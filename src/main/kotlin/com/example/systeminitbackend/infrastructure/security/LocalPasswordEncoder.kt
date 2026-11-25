package com.example.systeminitbackend.infrastructure.security

import com.example.systeminitbackend.domain.layer0.account.PasswordEncoder
import org.springframework.stereotype.Component
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

/**
 * 로컬 개발용 PasswordEncoder. 외부 의존성 없이 JDK의 PBKDF2(HmacSHA256)로 해시한다.
 * 저장 형식: pbkdf2${반복횟수}${salt(Base64)}${hash(Base64)}
 *
 * 운영 전환 시 BCrypt 등 검증된 구현체로 교체한다.
 */
@Component
class LocalPasswordEncoder : PasswordEncoder {

    private val secureRandom = SecureRandom()

    override fun encode(rawPassword: String): String {
        val salt = ByteArray(SALT_BYTES).also(secureRandom::nextBytes)
        val hash = hash(rawPassword, salt, ITERATIONS, KEY_LENGTH_BITS)
        val encoder = Base64.getEncoder()
        return listOf(PREFIX, ITERATIONS, encoder.encodeToString(salt), encoder.encodeToString(hash))
            .joinToString(SEPARATOR)
    }

    override fun matches(rawPassword: String, encodedPassword: String): Boolean {
        val parts = encodedPassword.split(SEPARATOR)
        if (parts.size != 4 || parts[0] != PREFIX) return false

        val iterations = parts[1].toIntOrNull()?.takeIf { it > 0 } ?: return false
        val decoder = Base64.getDecoder()
        val (salt, expected) = try {
            decoder.decode(parts[2]) to decoder.decode(parts[3])
        } catch (e: IllegalArgumentException) {
            return false
        }
        if (salt.isEmpty() || expected.isEmpty()) return false

        val actual = hash(rawPassword, salt, iterations, expected.size * Byte.SIZE_BITS)
        // 비교 시간으로 일치 여부가 드러나지 않도록 상수 시간 비교를 사용한다.
        return MessageDigest.isEqual(expected, actual)
    }

    private fun hash(rawPassword: String, salt: ByteArray, iterations: Int, keyLengthBits: Int): ByteArray {
        val spec = PBEKeySpec(rawPassword.toCharArray(), salt, iterations, keyLengthBits)
        return try {
            SecretKeyFactory.getInstance(ALGORITHM).generateSecret(spec).encoded
        } finally {
            spec.clearPassword()
        }
    }

    private companion object {
        const val PREFIX = "pbkdf2"
        const val SEPARATOR = "$"
        const val ALGORITHM = "PBKDF2WithHmacSHA256"
        const val ITERATIONS = 210_000
        const val SALT_BYTES = 16
        const val KEY_LENGTH_BITS = 256
    }
}
