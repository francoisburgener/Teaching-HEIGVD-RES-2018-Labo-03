# RES-2018-Labo-SMTP

## Description

In this lab, we developped a client application (TCP) in Java that use a the socket API to comminucate with a SMTP server. In order to do the right thing we watched the teacher's webcast and followed the instructions. Our lab consist of  creating a "prank generator". The user will define a list of emails and messages that the client will use to forge mails and send them to a chosen SMTP server.

## Instructions

### Setting up the prank campaign

_"A great prank campaign implies a great preparation."_ - Ben Parker, probably

In order to lead this campaign, you have to prepare the SMTP server, the list of the poor lamb that will be pranked, and a list of ~~message~~ prank that will be randomly taken to be sent. In the "SMTP/config" folder you will find the configuration file cleverly named ___config.properties___. It's in this file that we specify the IP address and port of the SMTP server to use, the number of group that we have to create with the list of email. We also have to give the locations of the files containning the victims and messages list, files that you can find in the same folder.

For the victims list, you simply need to give the mail address, one per line, no big deal. For the messages list, you need a bit more skills, but don't worry we're here to explain this ;). First of all, you have to write down the subject of the mail. In order to do so, juste write "__Subject : XXX__" and replace the __XXX__ by a subject that please your heart. THEN you can _let it go (♪♫)_, and write the content of your prank. Once you're satisfied with your first prank, and your imagination leads you to more wonderful jokes, simply write _a line_ that consists of "__@@__" (a double "at") and repeat the prank writing process. 

Congrats! you set up the prank campaign !

### "But what if I want to test it ?"

We planned it, buddy! In the "Docker" folder, you'll find the Dockerfile that will run a MockMock server. Here's the content of this file :

```Dockerfile
FROM java:8

ADD ["https://github.com/tweakers-dev/MockMock/blob/master/release/MockMock.jar?raw=true", "/mail/MockMock.jar"]

CMD ["java", "-jar", "/mail/MockMock.jar"]
```

To use it, open your Docker Terminal (take note of the IP address, you'll have to use it) and get in the Dockerfile location. Then you have to build the image of the server with this command (you don't have to use the same name) :

```cmd
docker build -t NameOfTheImage
```

You can check if it was correctly created by typing the following command :

```cmd
docker images
```

Finally, to run your server, use this command : 

```cmd
docker run -p 8282:8282 -p 25:25 NameOfTheImage
```

the parameter "__-p x:y__" __bind the local port x to the server port y__. In our case, the port 8282 is for the HTTP server (you'll be able to see the mail sent through a web interface), and the port 25 is used for the SMTP server. "_I don't like theses numbers can i change them?_" - YES YOU CAN ! __but only the ones before the colons__ (":", the number __x__).

___A little reminder___ : don't forget to change the server address and port in config.properties. Use the ip given at the start of the docker terminal (i told you it was important), and the local port you binded on the server port 25.

Last but not least : give yourself a little treat (a cake, a cookie, a camomile tea, ...), you take the prank seriously and want to test your configuration, you deserved this!

### Prank time baby !

NOW it's serious business! Prank campaign, ready ✓. SMTP server, checked ✓. 

You just have to send those bombs now! In order to do so, open a terminal et go to the project folder. Then execute the jar file. If you don't give any argument to the file it will use the default configuration file (/config/config.properties). Anyway you still can use another properties file by giving the path.

And THERE YOU GO BABY, your prank campaign is complete, you can be proud of yourself!

## Implementation

"oh thank you my lords, but how did you build this ingenious mechanism?" - Well if you insist, I'll show you.

___A concise description of your implementation: document the key aspects of your code. It is probably a good idea to start with a class diagram. Decide which classes you want to show (focus on the important ones) and describe their responsibilities in text. It is also certainly a good idea to include examples of dialogues between your client and a SMTP server (maybe you also want to include some screenshots here).___

