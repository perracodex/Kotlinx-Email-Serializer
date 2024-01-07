/*
 * Copyright (c) 2024 Perraco Labs. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>
 */

package perraco.utils

object SecurityUtils {

    /**
     * Verifies if an email address is in the correct format.
     * It adheres to the RFC 5321 specification, which defines the standard format for email addresses.
     *
     * The top-level domain (TLD) must be at least two characters long.
     * The total length of the email address must not exceed 254 characters (as per RFC 5321).
     *
     * The local part of the email (before the '@') allows:
     *      • Uppercase and lowercase letters (A-Z, a-z)
     *      • Digits (0-9)
     *      • Characters: dot (.), underscore (_), percent (%), plus (+), hyphen (-)
     *      • Maximum length of 64 characters (as per RFC 5321)
     * 
     * The domain part of the email (after the '@') can include:
     *      • Letters (A-Z, a-z)
     *      • Digits (0-9)
     *      • Hyphens (-)
     *
     * Examples of valid email formats:
     *      • example@email.com
     *      • user.name+tag+sorting@example.co.uk
     *      • user_name@example.org
     *      • username@example.travel
     *      • user1234@example-company.com
     *
     * Examples of invalid email formats:
     *      • any-plain-text
     *      • @no-local-part.com
     *      • .email@example.com (local part starts with a dot)
     *      • email.@example.com (local part ends with a dot)
     *      • email..email@example.com (local part has consecutive dots)
     *      • email@example (no top-level domain)
     *      • email@example...com (top-level domain has consecutive dots)
     *
     * @param email The email address to check.
     * @return True if the email is valid according to the regex pattern and RFC 5321, false otherwise.
     */
    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}$"
        if (!email.matches(emailRegex.toRegex())) {
            return false
        }

        // Check for the maximum length of the entire email address (254 characters as per RFC 5321).
        if (email.length > 254) {
            return false
        }

        // Splitting local and domain parts to apply specific checks.
        val parts = email.split("@")
        val localPart = parts[0]
        val domainPart = parts[1]

        // Check for the maximum length of the local part (64 characters as per RFC 5321).
        if (localPart.length > 64) {
            return false
        }

        // Ensure domain part does not have consecutive dots.
        if (domainPart.contains("..")) {
            return false
        }

        // Check if the local part starts or ends with a dot, or contains consecutive dots.
        if (localPart.startsWith(".") || localPart.endsWith(".") || localPart.contains("..")) {
            return false
        }

        return true
    }
}
