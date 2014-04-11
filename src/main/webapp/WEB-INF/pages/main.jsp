<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jSoup Codenvy Sample</title>
</head>
<body>
  <h2>Enter URL (without http://): </h2>
    <form action="site">
        <label for="url">URL: </label>
        <input id="url" type="text" name="url" size="50" />
        <input type="submit" value="Get info!" onsubmit=""/>
    </form>
</body>
</html>