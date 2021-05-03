$(document).ready(function () {
    $('#akronInfoDiv').hide();
    $('#minneapolisInfoDiv').hide();
    $('#louisvilleInfoDiv').hide();

    $('#mainButton').on('click', function () {
        $('#mainInfoDiv').show('slow');
        $('#akronInfoDiv').hide('slow');
        $('#minneapolisInfoDiv').hide('slow');
        $('#louisvilleInfoDiv').hide('slow');
    })
    $('#akronButton').on('click', function () {
        $('#mainInfoDiv').hide('slow');
        $('#akronInfoDiv').show('slow');
        $('#akronWeather').hide();
        $('#minneapolisInfoDiv').hide('slow');
        $('#louisvilleInfoDiv').hide('slow');
    })
    $('#minneapolisButton').on('click', function () {
        $('#mainInfoDiv').hide('slow');
        $('#akronInfoDiv').hide('slow');
        $('#minneapolisInfoDiv').show('slow');
        $('#minneapolisWeather').hide();
        $('#louisvilleInfoDiv').hide('slow');
    })
    $('#louisvilleButton').on('click', function () {
        $('#mainInfoDiv').hide('slow');
        $('#akronInfoDiv').hide('slow');
        $('#minneapolisInfoDiv').hide('slow');
        $('#louisvilleInfoDiv').show('slow');
        $('#louisvilleWeather').hide();
    })

    $('#akronWeatherButton').on('click', function () {
        $('#akronWeather').toggle('slow');
    })
    $('#minneapolisWeatherButton').on('click', function () {
        $('#minneapolisWeather').toggle('slow');
    })
    $('#louisvilleWeatherButton').on('click', function () {
        $('#louisvilleWeather').toggle('slow');
    })

    $('tr').hover(
        // in callback
        function () {
            $(this).css("background-color", "WhiteSmoke");
            $('th').css("background-color", "AliceBlue");
        },
        // out callback
        function () {
            $(this).css("background-color", "");
        }
    );
});
