# Build

One needs GraalVM and Maven to do the native build.

```
$ mvn clean package -Pnative
```

Results in an executable file:  ```target/qjf-runner``` for your platform.

On contemporary Linux distribution, you can symlink it to your bin dir: e.g. ```$ ln -s /home/karm/workspaceRH/qjf/target/qjf-runner /home/karm/bin/qjf```

# Usage

Pipe unformatted JSON to the executable:

```
$ curl  'https://start.microprofile.io/api/supportMatrix'  | qjf

```
