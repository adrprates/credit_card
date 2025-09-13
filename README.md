# 💳 Credit Card Input Demo

A simple, interactive **Credit Card Input** for Android, with real-time formatting and validation.  
It mimics a real credit card form, making it intuitive for users to input card details safely and cleanly.

<div align="center">
  <img src="app/src/main/res/layout.jpg" alt="layout" width="400"/>
</div>

---

## 🚀 Features

### Card Number Formatting
Automatically adds spaces every 4 digits:  
`1234 5678 9012 3456`

### Expiry Date Formatting
Auto-formats as `MM/AA` while typing.

### CVV Input
Limited to 3 digits, aligned with the CVV label.

### Card Holder Name
Minimum 3 characters, inline validation.

### Inline Validation
No submit button needed — each field validates on focus lost:  

- **Card number:** exactly 16 digits  
- **Expiry:** MM/AA format  
- **CVV:** 3 digits  
- **Card holder name:** at least 3 characters

### Clean UI
Fields are aligned and styled to resemble a real credit card.

---
