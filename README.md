# Clean-Architecture-Core-Template-Plugin

This project introduces a plugin designed to minimize repetitive tasks in the Data and Domain Layers when implementing Clean Architecture. A common challenge in adopting Clean Architecture is the repetitive coding work in these layers. This plugin aims to reduce such redundant tasks, enabling developers to focus more efficiently on their projects.

### Automating the Data Layer
- Repository Impl(Class)
- DTO(Data Class)
- Data Source(Class)

### Automating the Domain Layer
- Repository(Interface)
- Entity(Data Class)
- UseCase(Class)

## How to use
1. Click `new` on the package or module you want to add files to.
2. Click `Other` and run ‘Clean Architecture Core Generator’
3. Enter `Prefix` and select the file to be created
4. Add your code to each file

![화면 기록 2024-04-11 오후 5 24 23](https://github.com/Songgyubin/Clean-Architecture-Core-Template-Plugin/assets/37494776/b266fadd-1473-4656-9947-33ada7cadf90)


## Installation

- Using the IDE built-in plugin system:

  Reviewing for distribution(04/11)
  
- Manually:

  Download the [latest release](https://github.com/Songgyubin/Clean-Architecture-Core-Template-Plugin/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

## Feedback & Questions
✉️ thd0427@gmail.com

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
