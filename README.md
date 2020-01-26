# TalkItOut #

### TalkItOut is an innovative mental health application for Android meant to connect clients and mental health practitioners. ###

TalkItOut allows for clients to share their daily thoughts with their practitioners through the app. This allows for practitioners to get real time information about their clients throughout the day and leading up to the next appointment. Using Natural Language Processing (NLP), TalkItOut is able to read through clients' logs in order to determine how they are feeling that day relative to previous entries in the app. This allows practitioners to leverage the power of AI to help understand their clients at a greater level and augment the effectiveness of their treatment.

### Technical Information ###

The app allows for users and practitioners to create an account and start right away with ease. Clients have to also register with the practitioners's username so ensure that their confidential information can only be seen by the right practitioner. 

Once logged in, clients can chose to write their thoughts or use speech-to-text to share their thoughts out loud. When this is done in this project, the text is sent through a network socket to connect to the NLP's Recurrent Neural Network in order to perform sentiment analysis using Stanford University CoreNLP's integrated toolkit to determine the overall feeling of the daily log. The log and the sentiment score is then available for the practitioner's eyes only.

Practitioners can view a 7-day trend of their client's mood based on the NLP's sentiment analysis to better help them develop their plan with the client.

### Screenshots ###

[graph]: https://github.com/DLabbate/TalkItOut/blob/master/pics/7DayTrend.png "7-day Trend"
[Mic]: https://github.com/DLabbate/TalkItOut/blob/master/pics/MicLog.png "Logging with Speech-to-Text"
[Logging]: https://github.com/DLabbate/TalkItOut/blob/master/pics/OptionsForLog.png "Options for Logging"
[Pract]: https://github.com/DLabbate/TalkItOut/blob/master/pics/PractitionerViewOfClients.png "Practitioner View of Clients"
[Sign-in]: https://github.com/DLabbate/TalkItOut/blob/master/pics/Signin.png "Sign-in"
[Texting]: https://github.com/DLabbate/TalkItOut/blob/master/pics/TextLog.png "Logging with Keyboard"

![Sign-in]

Sign-in Screen of App

![Logging]

Two Options for Logging

![Texting]

Daily Log with Keyboard

![Mic]

Daily Log with Speech-to-Text

![Pract]

Practitioner's List of Clients

![graph]

7-day Trend of Client's Scores
