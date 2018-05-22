<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h2 class="title"><spring:message code="about"/></h2>

1
down vote
favorite
1
Following is the folder structure of my application,

Application Folder Structure

I have created a resource folder under webapp which has resource folders for CSS and JS

In the servlet-context file I have mapped the resources to resources/inspiremetheme as below,



em here is I am not able to load my JS files where as the CSS file is loaded properly. I was not able to understand what I have missed.

Update - 1: The file structure after unzipping is as below,

Inspireme
|
|------ META-INF
|
|------resources --> inspiremetheme --> css and js folders
|
|------ WEB-INF --> classes, jsp, lib, web.xml
java javascript css spring maven
shareimprove this question
edited Jul 3 '14 at 13:40

El Guapo
3,45553971
asked Jul 3 '14 at 13:28

Amarnath
6,31773973
Have you checked your .war file? Try unzipping (unjarring) it to see how it was built. – El Guapo Jul 3 '14 at 13:30
What about that "inspiremetheme" directory between "resources" and "js"? Your path doesn't include that. – Pointy Jul 3 '14 at 13:32
@Pointy I gave the path in the Spring context file.  Amarnath Jul 3 '14 at 13:34
Yes, but the> tag doesn't include it. The browser does not have any idea about your Spring configuration. – Pointy Jul 3 '14 at 13:39
@ElGuapo I have updated the file structure after unzipping. Please have a look. – Amarnath Jul 3 '14 at 13:41
show 2 more comments
1 Answer
active oldest votes

✕
up vote
4
down vote
accepted
You messedto script tags..

Also put a comment inside : this will prevent the jspx parser from minimizing the tag.

If parser minimize your tag you will have as result:


This can cause some browser not working