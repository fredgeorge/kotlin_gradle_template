# Instructions for Exploiting Template

Given a new <project name>, make the following changes:

- In general, rename template everywhere to <project name>
  - In setting.gradle.kts, rename kotlin-gradle-template to <project_name>
  - Update README.md to reflect the new <project name>
  - Update project structure to match new directory convention (com.nrkei.project.<project_name>)
  - Update build scripts to use new artifact IDs
  - Update documentation and comments to reflect new project name

If necessary, pull the initial template from
https://github.com/fredgeorge/kotlin_gradle_template
into a subdirectory of ~/src/kotlin
and rename it to <project name>. Make sure it is
a git repository with remote in https://github.com/fredgeorge
