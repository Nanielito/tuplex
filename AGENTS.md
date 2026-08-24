# Tuplex contributor guide

## Project context

Tuplex is a small, dependency-free Java 21 library of immutable tuples. Keep changes boring, focused, and backward-compatible unless a breaking change is explicitly requested.

Published coordinates: `io.github.nanielito:tuplex`. Java sources remain in `com.nan.tuplex`.

## Architecture

- `Tuple` is the sealed common API. Indexes are intentionally 1-based.
- `Tuple1` through `Tuple8` are fixed-arity records.
- `TupleN` handles arbitrary arity.
- `Tuples` is the factory entry point.
- Tuple values are immutable and non-null.
- Equality and hash codes must remain consistent across tuple implementations with the same values.

Before changing shared behavior, inspect every tuple implementation and its tests. Prefer fixing shared behavior once when possible; keep fixed-arity implementations consistent when duplication is unavoidable.

## Working rules

- Reuse existing patterns and the Java standard library; do not add dependencies without a concrete need.
- Keep the smallest correct diff. Avoid speculative abstractions and unrelated cleanup.
- Preserve Java 21 compatibility and public API behavior.
- Add or update the smallest relevant JUnit test for non-trivial behavior.
- Keep README examples accurate when user-facing behavior or installation changes.
- Do not edit generated build output or release versions by hand unless the task explicitly requires it.

## Verification

Run the narrowest relevant test while iterating, then before handoff run:

```shell
./gradlew clean build -PbuildJavaVersion=21
```

For publishing changes, also validate:

```shell
./gradlew nmcpCheckAggregationFiles -PbuildJavaVersion=21
```

Publishing validation requires the signing environment used by the release workflow.

## Git and releases

- Use Conventional Commits: `type(scope): description`, omitting the scope when it adds no value.
- Common types: `feat`, `fix`, `docs`, `test`, `refactor`, `build`, `ci`, and `chore`.
- Keep commits atomic. Do not commit, push, tag, or publish unless explicitly requested.
- Releases are created through `.github/workflows/release.yml`; do not publish artifacts manually as part of ordinary changes.
- Maven Central and GitHub Packages releases are immutable. If one publishing job fails, retry failed jobs instead of starting a new release run.

## Handoff and pull requests

Every completed change should report:

- What changed and why.
- Checks run and their result.
- Any remaining risk, skipped check, or required external step.

When asked for a PR summary, provide ready-to-paste Markdown with `Summary` and `Verification` sections. Add breaking changes, release notes, or follow-up steps only when applicable.
