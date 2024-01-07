# Kotlinx-Email-Serializer
An email serializer using the kotlinx serialization library, checking the format validity of an email address.

Requires the [kotlinX serialization](https://github.com/Kotlin/kotlinx.serialization) library:

Usagle:

```kotlin
@Serializable
data class ContactRequest(
    val email: EmailString,
    ....
)

```

### Constraints Verified:

#### Verification follows the format as per RFC 5321.

The total length of the email address must not exceed 254 characters (as per RFC 5321).

The top-level domain (TLD) must be at least two characters long.

#### The local part of the email (before the '@') allows:
* Uppercase and lowercase letters (A-Z, a-z)
* Digits (0-9)
* Characters: dot (.), underscore (_), percent (%), plus (+), hyphen (-)
* Maximum length of 64 characters (as per RFC 5321)

#### The domain part of the email (after the '@') can include:
* Letters (A-Z, a-z)
* Digits (0-9)
* Hyphens (-)
     
#### Examples of valid email formats:
* `example@email.com`
* `user.name+tag+sorting@example.co.uk`
* `user_name@example.org`
* `username@example.travel`
* `user1234@example-company.com`

#### Examples of invalid email formats:
* `any-plain-text`
* `@no-local-part.com`
* `.email@example.com` (local part starts with a dot)
* `email.@example.com` (local part ends with a dot)
* `email..email@example.com` (local part has consecutive dots)
* `email@example` (no top-level domain)
* `email@example...com` (top-level domain has consecutive dots)

