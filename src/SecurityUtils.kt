/*
 * Copyright (c) 2024 Perraco Labs. All rights reserved.
 * This work is licensed under the terms of the MIT license.
 * For a copy, see <https://opensource.org/licenses/MIT>
 */

package perraco.utils

object SecurityUtils {

    /**
     * Verifies if an email address is in the correct format.
     *
     * This function checks the format of the provided email address against a regular expression pattern.
     *
     * It allows for a range of characters in the local part of the email (before the '@'), including:
     *      • Uppercase and lowercase letters (A-Z, a-z)
     *      • Digits (0-9)
     *      • Some chars: dot (.), underscore (_), percent (%), plus (+), hyphen (-)
     * 
     * The domain part of the email (after the '@') can include:
     *      • Letters (A-Z, a-z)
     *      • Digits (0-9)
     *      • Hyphens (-)
     *
     * The top-level domain (TLD) must be at least two characters long.
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
     * @return True if the email is valid according to the regex pattern, false otherwise.
     */
    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}$"
        if (!email.matches(emailRegex.toRegex())) {
            return false
        }

        // Check for the maximum length of the entire email address (254 characters).
        if (email.length > 254) {
            return false
        }

        // Splitting local and domain parts to apply specific checks.
        val parts = email.split("@")
        val localPart = parts[0]
        val domainPart = parts[1]

        // Check for the maximum length of the local part (64 characters).
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
