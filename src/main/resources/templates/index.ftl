<#assign currentTime = "${.now?long?c}">

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <link rel="icon" href="./static/favicon.ico?v=1" type="image/x-icon">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <meta name="description" content="Web site created using create-react-app" />
    <link rel="manifest" href="./manifest.json" />
    <title>iot-admin</title>
    <link href="./static/css/main.css?v=${currentTime}" rel="stylesheet">
    <meta name="AppPublic" content=".">
</head>

<body>
    <noscript>You need to enable JavaScript to run this app.</noscript>
    <div id="root"></div>
    <script src="./static/js/commons.js"></script>
    <script src="./static/js/main.js?v=${currentTime}"></script>
    </body>
</html>
