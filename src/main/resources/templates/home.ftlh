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

<form action="/deviceautomation" method="post">

    <label for="listen_device">Device:</label>
    <select name="listenDeviceId" id="listen_device">
        <#list devices as device>
            <option value="${device.id}">${device.name}</option>
        </#list>
    </select>

    <label for="listen_state">Trigger state:</label>
    <select name="listenState" id="listen_state">
    </select>

    <label for="action_device">Action Device</label>
    <select name="actionDeviceId" id="action_device">
        <#list devices as device>
            <option value="${device.id}">${device.name}</option>
        </#list>
    </select>

    <label for="action_state">Action state:</label>
    <select name="actionState" id="action_state">
    </select>

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
        <th>Delete</th>
    </tr>
    <#list automations as automation>
        <tr>
            <td>${automation.listenDevice?has_content?then(automation.listenDevice.name, "NaN")}</td>
            <td>${automation.listenTopic}</td>
            <td>${automation.listenState}</td>
            <td>${automation.actionDevice?has_content?then(automation.actionDevice.name, "NaN")}</td>
            <td>${automation.actionTopic}</td>
            <td>${automation.actionState}</td>
            <td><a href="/automations/${automation.id}/delete">delete</a></td>
        </tr>
    </#list>
</table>
<script type="application/javascript">
    const queryDeviceStates = (deviceId, selectorQuery) => {
        console.log(deviceId);
        let path = '/api/devices/' + deviceId;
        fetch(path)
            .then(response => response.json())
            .then(deviceJson => {
                document.querySelector(selectorQuery).innerHTML = '';
                deviceJson["possibleStates"].forEach(state => {
                    let option = document.createElement("option");
                    option.value = state;
                    option.text = state;
                    document.querySelector(selectorQuery).appendChild(option);
                });
            });
    };

    window.onload = () => {
        let deviceSelector = document.querySelector("#listen_device");
        queryDeviceStates(deviceSelector.value, "#listen_state");
        let actionDeviceSelector = document.querySelector("#action_device");
        queryDeviceStates(actionDeviceSelector.value, "#action_state");
    }

    let deviceSelector = document.getElementById("listen_device");

    deviceSelector.addEventListener("change", function() {
        let deviceId = deviceSelector.value;
        queryDeviceStates(deviceId, "#listen_state");
    });

    let actionDeviceSelector = document.getElementById("action_device");

    actionDeviceSelector.addEventListener("change", function() {
        let deviceId = actionDeviceSelector.value;
        queryDeviceStates(deviceId, "#action_state");
    });
</script>
</body>
</html>