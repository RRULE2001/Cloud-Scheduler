<?php
$url = "https://reqbin.com/echo/get/json";

$json = file_get_contents($url);

echo $json;
?>