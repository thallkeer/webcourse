<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<ul id="trees">
    <li>
        1
        <ul class="box_hide">
            <li>1.1</li>
            <li>1.2</li>
            <li>1.3</li>
            <li>
                1.4
                <ul class="box_hide">
                    <li>1.4.1</li>
                    <li>1.4.2</li>
                    <li>1.4.3</li>
                    <li>1.4.4</li>
                    <li>1.4.5</li>
                    <li>1.4.6</li>
                </ul>
            </li>
        </ul>
    </li>
    <li>2</li>
    <li>3</li>
    <li>4</li>
    <li>
        5
        <ul class="box_hide">
            <li>5.1</li>
            <li>5.2</li>
            <li>5.3</li>
            <li>5.4</li>
        </ul>
    </li>
</ul>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script>
    $(document).ready(function(){
        $('#trees').find("li").click(function(event){
            $(this).children("ul").toggleClass("box_hide");
            event.stopPropagation();
        });
    });
</script>
</body>
</html>