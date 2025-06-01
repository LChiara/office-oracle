# Office Oracle Project

## Overview

The Office Oracle project is a multi-module application consisting of a backend built with Quarkus and a frontend built with React, TypeScript, and Vite. It provides a chat interface powered by WebSockets and integrates with LangChain4j for AI-driven responses.

## Project Structure
```
. ├── backend/ 
│ ├── src/ 
│ │ └── main/java/com/example/officeoracle/ 
│ │   ├── OracleResource.java 
│ │   ├── OracleChatSocket.java 
│ │   └── OracleAgent.java 
│ └── pom.xml 
├── ui/ 
│ ├── src/ 
│ │ ├── main.tsx 
│ │ ├── App.css 
│ │ └── vite-env.d.ts 
│ ├── index.html 
│ ├── package.json 
│ ├── tsconfig.json 
│ └── vite.config.ts 
└── README.md
```

## Backend

### Features
- Built with Quarkus
- WebSocket endpoint at `/oracle-chat`
- Integration with LangChain4j for AI-driven responses

### Dependencies
- Quarkus
- LangChain4j
- Rest-Assured (for testing)

### Build and Run
To build and run the backend:
```sh
./mvnw clean package
java -jar target/officeoracle-1.0.0-SNAPSHOT.jar
```

## Frontend
Features:
- Built with React, TypeScript, and Vite

### Scripts
```
npm run dev: Start the development server
npm run build: Build the application
npm run lint: Run ESLint
npm run preview: Preview the production build
```

### Build and Run
To build and run the frontend:
```
cd ui
npm install
npm run dev
```

## Known Issues
- WebSocket endpoint OracleChatSocket may throw ModelNotFoundException if the AI model is not configured correctly.
- Unrecognized configuration keys in application.properties may cause warnings.
Project is still under development.