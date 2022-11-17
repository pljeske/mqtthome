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

<form action="/devices" method="post">
    <label for="name">Device name:</label>
    <input id="name" type="text" name="name"/>
    <br/><br/>
    <label for="topic">Device topic:</label>
    <textarea id="topic" name="topic"></textarea>
    <br/><br/>
    <label for="possible_states">Possible states (divided by ","):</label>
    <textarea rows="2" id="possible_states" name="possibleStatesString"></textarea>
    <br/><br/>
    <input type="submit" value="Submit"/>
    <input type="reset" value="Reset"/>
</form>
<table>
    <tr>
        <th>Device</th>
        <th>Topic</th>
        <th>Possible states</th>
    </tr>
    <#list devices as device>
        <#assign numberOfStates = device.possibleStates?size>
        <tr>
            <td>${device.name}</td>
            <td>${device.topic}</td>
            <td>
                <ul>
                    <#list device.possibleStates as state>
                        <li>${state}</li>
                    </#list>
                </ul>
            </td>
        </tr>
    </#list>
</table>
<!--<script src="/js/main.js"></script>-->
</body>
</html>