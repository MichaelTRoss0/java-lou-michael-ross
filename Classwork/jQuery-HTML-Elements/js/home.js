$(document).ready(function () {
    $('H1').addClass('text-center');
    $('H2').addClass('text-center');

    $('#myBanner').removeClass('row');
    $('#myBanner').addClass('page-header');

    $('#yellowHeading').text('YellowTeam');

    $('#orangeTeamList').css('background-color', 'orange');
    $('#blueTeamList').css('background-color', 'blue');
    $('#redTeamList').css('background-color', 'red');
    $('#yellowTeamList').css('background-color', 'yellow');

    $('#yellowTeamList').append('<li>Joseph Banks</li>');
    $('#yellowTeamList').append('<li>Simon Jones</li>');

    $('#oops').hide();

    $('#footerPlaceholder').remove();

    $('#footer').append(
        '<p id="footerInfo">Michael Ross<br/>miketross0@gmail.com</p>');
    $('#footerInfo').css('font-family', 'Courier');
    $('#footerInfo').css('font-size', '24px');
});
