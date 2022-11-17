<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mqtt Home</title>
    <link rel="stylesheet" type="text/css" href="<@spring.url 'main.css'/>"/>
</head>
<body>
<h1>Mqtt Home</h1>

<form action="/automation" method="post">
    <label for="listen_topic">Listen topic:</label>
    <input type="text" name="listenTopic" id="listen_topic"/>
    <label for="listen_state">Listen state:</label>
    <input name="listenState" id="listen_state"/>
    <label for="action_topic">Action topic:</label>
    <input name="actionTopic" id="action_topic"/>
    <label for="action_state">Action state:</label>
    <input name="actionState" id="action_state"/>
    <input type="submit" value="Submit"/>
    <input type="reset" value="Reset"/>
</form>
<table>
    <tr>
        <th>Listen Device</th>
        <th>Listen topic</th>
        <th>Liste state</th>
        <th>Action Device</th>
        <th>Action topic</th>
        <th>Action state</th>
    </tr>
    <#list automations as automation>
        <tr>
            <td>${automation.listenDevice!"NaN"}</td>
            <td>${automation.listenTopic}</td>
            <td>${automation.listenState}</td>
            <td>${automation.actionDevice!"NaN"}</td>
            <td>${automation.actionTopic}</td>
            <td>${automation.actionState}</td>
        </tr>
    </#list>
</table>
</body>
</html>