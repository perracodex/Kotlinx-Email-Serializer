# Kotlinx-Email-Serializer
An email serializer for the kotlinx serialization library, checking the format validity of an email.

Usagle:

```kotlin
@Serializable
data class ContactRequest(
    val email: EmailString,
    ....
)

```

### Constrains verfied:

It allows for a range of characters in the local part of the email (before the '@'), including:
```
     • Uppercase and lowercase letters (A-Z, a-z)
     • Digits (0-9)
     • Some chars: dot (.), underscore (_), percent (%), plus (+), hyphen (-)
```
The domain part of the email (after the '@') can include:
```
     • Letters (A-Z, a-z)
     • Digits (0-9)
     • Hyphens (-)
```

The top-level domain (TLD) must be at least two characters long.

Examples of valid email formats:
```
     • example@email.com
     • user.name+tag+sorting@example.co.uk
     • user_name@example.org
     • username@example.travel
     • user1234@example-company.com
```
Examples of invalid email formats:
```
     • plainText
     • @no-local-part.com
     • .email@example.com (local part starts with a dot)
     • email.@example.com (local part ends with a dot)
     • email..email@example.com (local part has consecutive dots)
     • email@example (no top-level domain)
     • email@example...com (top-level domain has consecutive dots)
```
   
