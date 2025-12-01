# 🏋️ Gym Management System – Java Swing

This project is a **desktop application built in Java using Swing** to manage gym operations with an interactive user interface. The system makes it easy to handle **members, trainers, membership plans, product purchases, and payments** — all inside a modern and responsive UI.

---

## 🚀 Features
✔ Add and manage Gym Members  
✔ Assign Trainers & Membership Plans  
✔ Product list and shopping cart system  
✔ Automatic total price + discount calculation  
✔ Payment confirmation with invoice popup  
✔ Back navigation and multi-screen UI system  
✔ Attractive dark theme UI with custom colors  

---

## 🛠 Tech Stack
| Technology | Usage |
|-----------|--------|
| Java | Core logic |
| Swing | GUI and layout |
| AWT | UI elements |
| Collections | Data storage (ArrayList) |

---


## 📂 Project Structure
GymManagementSystem.java → Main application with UI & logic
Member → (Inner class for member data)
Trainer → (Inner class for trainer data)
MembershipPlan → (Inner class for plan data)
Product → (Inner class for product data)

yaml
Copy code

> ⚠ Note: This project uses in-memory lists (`ArrayList`) instead of a database. Data resets when the app is restarted.

---

## ▶️ How to Run
1. Install **Java JDK (8 or higher)**
2. Open the folder in **VS Code / IntelliJ / Eclipse**
3. Compile and run the file:

```sh
javac GymManagementSystem.java
java GymManagementSystem
Or simply press Run ▶ if using an IDE.

📸 Screens (UI Pages Included)
Welcome Dashboard

Member Registration

Trainer Selection

Plan Selection

Product Purchase

Payment & Invoice

🔮 Future Improvements (Optional Enhancements)
🔹 Database integration (MySQL/Firebase)
🔹 Export invoice PDF
🔹 Login system for admin and staff
🔹 Report and analytics dashboard

🤝 Contributing
Pull requests and suggestions are welcome!
If you have ideas to improve or extend the system, feel free to contribute.
