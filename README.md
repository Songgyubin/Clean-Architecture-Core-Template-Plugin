# Clean-Architecture-Core-Template-Plugin
<!-- Plugin description -->
### Eng
This project introduces a plugin designed to minimize repetitive tasks in the Data and Domain Layers when implementing Clean Architecture.

A common challenge in adopting Clean Architecture is the repetitive coding work in these layers.

This plugin aims to reduce such redundant tasks, enabling developers to focus more efficiently on their projects.


### Kor
이 프로젝트는 클린 아키텍처의 데이터와 도메인 레이어 코드를 생성하기 위한 플러그인입니다.

이 플러그인은 클린 아키텍처의 핵심 구성 요소를 효율적으로 생성할 수 있도록 하여 개발 프로세스를 단순화하고 능률화합니다.

접두사를 지정할 수 있으며, 각 레이어에서 생성할 수 있는 클래스와 인터페이스는 다음과 같습니다.

![화면 기록 2024-04-11 오후 5 24 23](https://github.com/Songgyubin/Clean-Architecture-Core-Template-Plugin/assets/37494776/b266fadd-1473-4656-9947-33ada7cadf90)
<!-- Plugin description end -->
### Automating the Data Layer
- Repository Impl(Class)
- DTO(Data Class)
- Data Source(Class)

### Automating the Domain Layer
- Repository(Interface)
- Entity(Data Class)
- UseCase(Class)

## How to use
### Eng
1. Click `new` on the package or module you want to add files to.
2. Click `Other` and run `Clean Architecture Core Generator`
3. Enter `Prefix` and select the file to be created
4. Add your code to each file

### Kor
1. 파일을 추가하려는 패키지나 모듈에서 `New`를 클릭합니다.
2. `Other`를 클릭하고 `Clean Architecture Core Generator`를 실행합니다.
3. `Prefix`를 입력하고 생성할 파일을 선택합니다.
4. 각 파일에 코드를 추가하세요.

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
