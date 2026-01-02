# MedicalFinder - Healthcare Management System

![MedicalFinder Banner](https://img.shields.io/badge/MedicalFinder-Healthcare%20Management-blue)
![JavaFX](https://img.shields.io/badge/JavaFX-17.0.2-orange)
![PHP](https://img.shields.io/badge/PHP-Symfony%205-green)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue)
![License](https://img.shields.io/badge/License-MIT-green)

MedicalFinder is a comprehensive healthcare management system featuring three distinct user portals (Admin, Doctor, Patient) with map-based facility/doctor location, appointment scheduling, and medical record management.

## 📋 Table of Contents
- [Features](#-features)
- [Architecture](#-architecture)
- [Tech Stack](#-tech-stack)
- [Installation](#-installation)
- [Project Structure](#-project-structure)
- [Configuration](#-configuration)
- [Running the Application](#-running-the-application)
- [API Documentation](#-api-documentation)
- [Development](#-development)
- [Testing](#-testing)
- [Deployment](#-deployment)
- [Contributing](#-contributing)
- [License](#-license)
- [Support](#-support)

## ✨ Features

### 🔐 Authentication & User Management
- **Multi-role authentication** (Admin, Doctor, Patient)
- Secure registration with email verification
- Password recovery system
- Role-based access control (RBAC)
- Session management

### 👨‍⚕️ Admin Portal
- **Dashboard Analytics**: System overview with statistics
- **Facility Management**: Add/edit/delete medical facilities
- **Doctor Management**: Verify, suspend, and manage doctors
- **User Management**: Manage all system users
- **Reports & Analytics**: Generate detailed reports (PDF/Excel export)
- **System Configuration**: Configure application settings

### 🩺 Doctor Portal
- **Professional Dashboard**: Daily schedule and statistics
- **Appointment Management**: View, confirm, reschedule appointments
- **Availability Management**: Set working hours and recurring patterns
- **Patient Records**: Access patient medical history
- **Virtual Consultation**: Integrated video consultation interface
- **Prescription Management**: Create and manage prescriptions

### 👤 Patient Portal
- **Health Dashboard**: Personal health overview
- **Map-Based Finder**: Interactive map to find doctors/facilities
- **Appointment Booking**: Step-by-step booking wizard
- **Medical Profile**: Complete health information management
- **Virtual Consultation**: Join video consultations
- **Appointment History**: Track past and upcoming appointments

### 🗺️ Map Features
- **Interactive Map**: Leaflet.js with OpenStreetMap integration
- **Location Search**: Find healthcare providers by location
- **Distance Filtering**: Filter by radius/distance
- **Real-time Availability**: Check doctor availability
- **Directions Integration**: Get directions to facilities

### 📱 Additional Features
- **Notifications**: Real-time notifications system
- **File Upload**: Document and image uploads
- **Export Options**: PDF and Excel reports
- **Multi-language Support**: Internationalization ready
- **Responsive Design**: Works on desktop and tablets
- **Dark/Light Theme**: User-selectable themes

## 🏗️ Architecture

MedicalFinder follows a **modular microservices architecture** with clear separation between:

### Backend (Symfony PHP 5)
- **RESTful API** with JSON responses
- **JWT Authentication** for secure API access
- **Doctrine ORM** for database operations
- **Event-driven architecture** for notifications
- **Queue system** for background tasks

### Frontend (JavaFX Desktop Application)
- **MVC Pattern** with clear separation of concerns
- **Service Layer** for business logic
- **Repository Pattern** for data access
- **Reusable Components** for consistent UI
- **WebView Integration** for map functionality

### Database
- **MySQL 8.0+** relational database
- **Optimized indexes** for performance
- **Data encryption** for sensitive information
- **Backup and recovery** procedures

## 🛠️ Tech Stack

### Backend (Symfony PHP 5)
- **PHP 7.4+** with Symfony 5.4
- **MySQL 8.0+** with Doctrine ORM
- **JWT Authentication** (lexik/jwt-authentication-bundle)
- **API Platform** for REST API generation
- **Monolog** for logging
- **SwiftMailer** for email notifications
- **RabbitMQ** for message queue (optional)

### Frontend (JavaFX)
- **Java 11+** with JavaFX 17
- **Maven** for dependency management
- **AtlantaFX** for modern UI components
- **Leaflet.js** for map functionality
- **Jackson** for JSON processing
- **OkHttp** for HTTP requests
- **ControlsFX** for additional UI controls
- **FontAwesomeFX** for icons

### Development Tools
- **Docker & Docker Compose** for containerization
- **PHPUnit** for PHP testing
- **JUnit 5** for Java testing
- **Postman** for API testing
- **Git** for version control
- **GitHub Actions** for CI/CD

## 📥 Installation

### Prerequisites

```bash
# System Requirements
- Java 11 or higher
- PHP 7.4 or higher
- MySQL 8.0 or higher
- Apache/Nginx web server
- Composer (for PHP dependencies)
- Maven 3.6+ (for Java dependencies)
```

### Backend Setup (Symfony)

1. **Clone the repository**
```bash
git clone https://github.com/yourusername/medicalfinder.git
cd medicalfinder/backend
```

2. **Install PHP dependencies**
```bash
composer install
```

3. **Configure environment**
```bash
cp .env.example .env
# Edit .env with your database credentials
```

4. **Database setup**
```bash
# Create database
php bin/console doctrine:database:create

# Run migrations
php bin/console doctrine:migrations:migrate

# Load fixtures (optional)
php bin/console doctrine:fixtures:load
```

5. **Generate JWT keys**
```bash
mkdir -p config/jwt
openssl genpkey -out config/jwt/private.pem -aes256 -algorithm rsa -pkeyopt rsa_keygen_bits:4096
openssl pkey -in config/jwt/private.pem -out config/jwt/public.pem -pubout
```

### Frontend Setup (JavaFX)

1. **Navigate to frontend directory**
```bash
cd ../frontend
```

2. **Configure application properties**
```bash
# Edit src/main/resources/config/application.properties
# Set API endpoint and other configurations
```

3. **Build the application**
```bash
mvn clean compile
```

### Docker Setup (Alternative)

```yaml
# docker-compose.yml
version: '3.8'
services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: medicalfinder
      MYSQL_USER: medicalfinder
      MYSQL_PASSWORD: medicalfinder
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

  backend:
    build: ./backend
    ports:
      - "8000:8000"
    depends_on:
      - mysql
    environment:
      DATABASE_URL: mysql://medicalfinder:medicalfinder@mysql:3306/medicalfinder

  frontend:
    build: ./frontend
    ports:
      - "8080:8080"
    depends_on:
      - backend

volumes:
  mysql_data:
```

## 📁 Project Structure

```
MedicalFinder/
├── backend/                          # Symfony PHP Backend
│   ├── src/
│   │   ├── Controller/              # API Controllers
│   │   ├── Entity/                  # Doctrine Entities
│   │   ├── Repository/              # Data Repositories
│   │   ├── Service/                 # Business Logic
│   │   ├── Event/                   # Event Listeners
│   │   └── Security/                # Security Configuration
│   ├── config/                      # Symfony Configuration
│   ├── migrations/                  # Database Migrations
│   ├── tests/                       # PHPUnit Tests
│   └── public/                      # Web Root
│
├── frontend/                        # JavaFX Desktop Application
│   ├── src/main/java/com/medicalfinder/
│   │   ├── app/                     # Application Entry Point
│   │   ├── controllers/             # FXML Controllers
│   │   │   ├── auth/                # Authentication Controllers
│   │   │   ├── admin/               # Admin Portal Controllers
│   │   │   ├── doctor/              # Doctor Portal Controllers
│   │   │   ├── patient/             # Patient Portal Controllers
│   │   │   └── shared/              # Shared Controllers
│   │   ├── models/                  # Data Models
│   │   ├── services/                # Business Services
│   │   │   ├── auth/                # Authentication Services
│   │   │   ├── database/            # Database Services
│   │   │   ├── map/                 # Map Services
│   │   │   ├── appointment/         # Appointment Services
│   │   │   └── file/                # File Services
│   │   ├── utils/                   # Utility Classes
│   │   └── exceptions/              # Custom Exceptions
│   │
│   ├── src/main/resources/
│   │   ├── com/medicalfinder/views/ # FXML Views
│   │   │   ├── auth/                # Authentication Views
│   │   │   ├── layouts/             # Layout Components
│   │   │   ├── admin/               # Admin Portal Views
│   │   │   ├── doctor/              # Doctor Portal Views
│   │   │   ├── patient/             # Patient Portal Views
│   │   │   ├── shared/              # Shared Views
│   │   │   └── components/          # Reusable Components
│   │   ├── css/                     # Stylesheets
│   │   ├── images/                  # Images and Icons
│   │   ├── config/                  # Configuration Files
│   │   └── maps/                    # Map HTML/JS Files
│   │
│   ├── src/test/                    # Unit Tests
│   └── pom.xml                      # Maven Configuration
│
├── database/                        # Database Scripts
│   ├── schema.sql                   # Database Schema
│   ├── seed_data.sql                # Initial Data
│   └── migrations/                  # Migration Scripts
│
├── docs/                            # Documentation
│   ├── api/                         # API Documentation
│   ├── user-guide/                  # User Guides
│   └── developer/                   # Developer Documentation
│
├── scripts/                         # Utility Scripts
├── .github/                         # GitHub Actions
├── docker/                          # Docker Configuration
└── README.md                        # This File
```

## ⚙️ Configuration

### Backend Configuration (.env)

```env
# Database Configuration
DATABASE_URL=mysql://username:password@127.0.0.1:3306/medicalfinder

# JWT Configuration
JWT_SECRET_KEY=%kernel.project_dir%/config/jwt/private.pem
JWT_PUBLIC_KEY=%kernel.project_dir%/config/jwt/public.pem
JWT_PASSPHRASE=your-passphrase-here

# Application Settings
APP_ENV=dev
APP_SECRET=your-app-secret-here
APP_URL=http://localhost:8000

# Email Configuration
MAILER_DSN=smtp://user:pass@smtp.example.com:587
MAILER_FROM=no-reply@medicalfinder.com

# Map Services
GOOGLE_MAPS_API_KEY=your-google-maps-api-key
NOMINATIM_URL=https://nominatim.openstreetmap.org/search
```

### Frontend Configuration (application.properties)

```properties
# API Configuration
api.base.url=http://localhost:8000/api
api.timeout=30000

# Database Configuration
database.url=jdbc:mysql://localhost:3306/medicalfinder
database.username=medicalfinder
database.password=medicalfinder
database.pool.size=10

# Application Settings
app.name=MedicalFinder
app.version=1.0.0
app.theme=light

# Map Configuration
map.provider=openstreetmap
map.default.latitude=40.7128
map.default.longitude=-74.0060
map.default.zoom=12

# File Upload
upload.max.size=5242880  # 5MB
upload.allowed.types=image/*,.pdf,.doc,.docx
```

## 🚀 Running the Application

### Starting the Backend

```bash
# Development mode
cd backend
symfony server:start

# Or with PHP built-in server
php -S localhost:8000 -t public
```

### Starting the Frontend

```bash
# Development mode
cd frontend
mvn javafx:run

# Or build and run
mvn clean package
java -jar target/medicalfinder-1.0.0.jar
```

### Using Docker Compose

```bash
# Start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop services
docker-compose down
```

## 📚 API Documentation

### Authentication Endpoints

```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password",
  "role": "patient|doctor|admin"
}
```

### User Management

```http
GET /api/users
Authorization: Bearer {token}

POST /api/users
Content-Type: application/json

{
  "email": "newuser@example.com",
  "password": "password",
  "firstName": "John",
  "lastName": "Doe",
  "role": "patient"
}
```

### Appointment Management

```http
GET /api/appointments?status=upcoming&date=2024-01-15
Authorization: Bearer {token}

POST /api/appointments
Content-Type: application/json

{
  "doctorId": 1,
  "patientId": 2,
  "dateTime": "2024-01-15 10:00:00",
  "duration": 30,
  "type": "consultation",
  "symptoms": "Fever and cough"
}
```

### Facility Management

```http
GET /api/facilities?lat=40.7128&lng=-74.0060&radius=10
Authorization: Bearer {token}
```

## 🧪 Testing

### Backend Tests

```bash
# Run all tests
cd backend
php bin/phpunit

# Run specific test suite
php bin/phpunit --testsuite=unit
php bin/phpunit --testsuite=functional
php bin/phpunit --testsuite=integration
```

### Frontend Tests

```bash
# Run unit tests
cd frontend
mvn test

# Run with coverage
mvn clean test jacoco:report
```

### API Testing with Postman

1. Import the Postman collection from `docs/api/MedicalFinder.postman_collection.json`
2. Set environment variables in Postman
3. Run the collection tests

## 🚀 Deployment

### Production Backend Deployment

1. **Prepare production environment**
```bash
# Set production environment
APP_ENV=prod
APP_DEBUG=0

# Optimize autoloader
composer install --no-dev --optimize-autoloader

# Clear and warmup cache
php bin/console cache:clear --env=prod
php bin/console cache:warmup --env=prod
```

2. **Database migration**
```bash
php bin/console doctrine:migrations:migrate --env=prod
```

3. **Setup web server (Nginx example)**
```nginx
server {
    listen 80;
    server_name api.medicalfinder.com;
    
    root /var/www/medicalfinder/backend/public;
    
    location / {
        try_files $uri /index.php$is_args$args;
    }
    
    location ~ ^/index\.php(/|$) {
        fastcgi_pass unix:/var/run/php/php7.4-fpm.sock;
        fastcgi_split_path_info ^(.+\.php)(/.*)$;
        include fastcgi_params;
        fastcgi_param SCRIPT_FILENAME $realpath_root$fastcgi_script_name;
        fastcgi_param DOCUMENT_ROOT $realpath_root;
    }
}
```

### Production Frontend Deployment

1. **Create distribution package**
```bash
mvn clean package -Pproduction
```

2. **Create installer (optional)**
```bash
# Using jpackage (Java 14+)
jpackage --input target/ \
  --name MedicalFinder \
  --main-jar medicalfinder-1.0.0.jar \
  --main-class com.medicalfinder.app.Main \
  --type dmg \
  --icon src/main/resources/images/icon.icns
```

### Docker Production Deployment

```bash
# Build and push images
docker build -t medicalfinder/backend:latest ./backend
docker build -t medicalfinder/frontend:latest ./frontend

# Deploy with docker-compose.prod.yml
docker-compose -f docker-compose.prod.yml up -d
```

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guidelines](CONTRIBUTING.md) for details.

### Development Workflow

1. **Fork the repository**
2. **Create a feature branch**
```bash
git checkout -b feature/amazing-feature
```
3. **Commit your changes**
```bash
git commit -m 'Add some amazing feature'
```
4. **Push to the branch**
```bash
git push origin feature/amazing-feature
```
5. **Open a Pull Request**

### Code Style

- **Java**: Follow Google Java Style Guide
- **PHP**: Follow PSR-12 coding standards
- **JavaScript**: Follow Airbnb JavaScript Style Guide
- **Commit Messages**: Use conventional commits format

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Support

### Documentation
- [User Guide](docs/user-guide/README.md)
- [Developer Guide](docs/developer/README.md)
- [API Reference](docs/api/README.md)

### Community
- [GitHub Issues](https://github.com/yourusername/medicalfinder/issues)
- [Discussions](https://github.com/yourusername/medicalfinder/discussions)
- [Wiki](https://github.com/yourusername/medicalfinder/wiki)

### Contact
- **Email**: support@medicalfinder.com
- **Twitter**: [@MedicalFinder](https://twitter.com/medicalfinder)
- **Website**: [medicalfinder.com](https://medicalfinder.com)

## 🙏 Acknowledgments

- **OpenStreetMap** for map data
- **Leaflet.js** for interactive maps
- **AtlantaFX** for JavaFX components
- **Symfony** for PHP framework
- **All contributors** who helped build MedicalFinder

---

<div align="center">
  Made with ❤️ for better healthcare management
</div>
