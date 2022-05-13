## 🐱 CatchACat - What is this project about?

The application was made for recrutitment process of [Miquido] company. Its purpose is to present list of facts about cats that was written by users and cat lovers.
Do you want catch a cat? Check it out!

## How the design looks like?

<img src="https://user-images.githubusercontent.com/45050205/168277566-057dd245-c443-4518-9d42-ed7e2d30f372.jpg" width="280">&nbsp;
<img src="https://user-images.githubusercontent.com/45050205/168277574-bb74c1d7-1513-4fc6-9cde-1e6d7af0485e.jpg" width="280">&nbsp; &nbsp;<br>

## What technologies was used?

To implement that project **Android** and **Kotlin** language was used with min. SDK 23 (Android 6.0). The program uses **Navigation Component** with NavGraph.

With the use of  **Clean Architecture** and  **MVVM** the presentation, business and data layers are separated. The data layer has two sources - CatFacts API handled by **Retrofit**, and local database implemented by **Room Database** which presists 30 facts.

Other technologies that was used: **LiveData, LiveEvent, Timber, Hilt (Dependency Injection)**
<br>

## Who is the author?

👨‍💻 Implemented by: [Wojciech Kula] <br>
🔗 API: [cat-facts]

[Miquido]: <https://www.miquido.com/>
[cat-facts]: <https://github.com/alexwohlbruck/cat-facts>
[Wojciech Kula]: <https://www.linkedin.com/in/wojciechkula/>