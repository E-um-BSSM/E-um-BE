# Repository Guidelines

## Project Structure & Module Organization
- Root uses a multi-module Gradle layout. Shared config lives in `build.gradle` and `settings.gradle`.
- Service code sits under `services/<service>/src/main/java/eum/<service>/...`; tests mirror this in `src/test/java`.
- `gateway/` and `common/` hold cross-cutting components (API gateway, shared types/utilities), while `lib/` contains vendored JARs pinned for builds.
- `docker-compose.yml` provisions per-service MySQL instances (ports 3307-3314). Start them with `docker-compose up -d` when running services locally.
- Consult `erd.txt` and `Default module.openapi.json` for data and API references.

## Build, Test, and Development Commands
- `./gradlew build` — assemble and run all tests across modules.
- `./gradlew :services:user:bootRun` (swap `user` for another module) — start a specific Spring Boot service.
- `./gradlew :services:user:test` — run tests for one module; use `./gradlew test` for the suite.
- `./gradlew clean` — drop build outputs; pair with `build` to refresh dependencies. Add `-x test` only when explicitly skipping tests.

## Coding Style & Naming Conventions
- Java 21 + Spring Boot 3.5; Lombok for boilerplate (`@Getter`, `@Builder`, `@NoArgsConstructor`).
- Use 4-space indentation, braces on the same line, and final modifiers where practical. Prefer constructor injection for components.
- Package layout follows `eum.<service>.<layer>` (e.g., `domain.entity`, `domain.dto.request/response`, `global.constclass`).
- Entity classes are singular (`User`, `ClassRoom`); DTOs end with `Request`/`Response`. Keep column names snake_case to match the existing schema.

## Testing Guidelines
- JUnit 5 via `spring-boot-starter-test`; security helpers come from `spring-security-test`.
- Place tests alongside code in `src/test/java`, naming classes `*Test`. Favor focused unit tests for domain logic and `@DataJpaTest`/`@SpringBootTest` for persistence or security flows.
- Ensure database-dependent tests target disposable schemas (Compose MySQL or an in-memory profile) and clean up created data.
- Expect new features to include tests for happy-path and guard-rail cases (permissions, null/blank input, boundary values).

## Commit & Pull Request Guidelines
- Follow the existing Conventional Commit style: `feat: …`, `fix: …`, `refactor: …` with a short, imperative summary; include Korean context only where it improves clarity.
- In PRs, describe scope, affected modules/services, DB/migration impacts, and link issues. Note how to reproduce/run (`./gradlew :services:<name>:test`, `docker-compose up -d`), and attach logs or screenshots if behavior changes.
