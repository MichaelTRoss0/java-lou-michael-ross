$(document).ready(function () {

    $.ajax({
        type: 'GET',
        url: 'http://tsg-vending.herokuapp.com/items',
        success: function (menuArray) {
            // get a reference to the 'vending-menu' div
            var vendingMenu = $('#vending-menu');

            // go through each of the returned menu items and append the info to the
            //vendingMenu
            $.each(menuArray, function (index, item) {
                var itemInfo = '<button class="menu-btn col-sm-4 col-md-4 col-lg-4" type="button" value="' + item.id + '">';
                itemInfo += '<b>' + item.id + '</b>: ' + item.name;
                itemInfo += '<br>';
                itemInfo += '$' + parseFloat(Math.round(item.price * 100) / 100).toFixed(2);
                itemInfo += '<br>';
                itemInfo += '<br>';
                itemInfo += 'Stock: ' + item.quantity;

                vendingMenu.append(itemInfo);
            })
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("FAILURE!");
        }
    });

    // $('.menu-btn').click(function () {
    //     var itemId = $(this).val();
    //     console.log(itemId);
    //     $('#item-select').val(itemId);
    // });

    $('menu').on('click', '.menu-btn', function () {
        var itemId = $(this).val();
        console.log(itemId);
        $('#item-select').val(itemId);
    });

    $('#purchase-btn').on('click', function () {
        var amountVar = $('#balance').val();
        var idVar = $('#item-select').val()
        $.ajax({
            type: 'POST',
            url: 'http://tsg-vending.herokuapp.com/money/' + amountVar + '/item/' + idVar,
            data: JSON.stringify({
                amount: amountVar,
                id: idVar
            }),
            success: function (coinArray) {
                var message = 'Thank you for your purchase!';
                document.getElementById('message-box').innerHTML = message;

                var money = 0.00;
                $('#balance').val(money);
                document.getElementById('balance').innerHTML = parseFloat(Math.round(money * 100) / 100).toFixed(2);

                var change = '';
                if (coinArray.quarters > 0) {
                    change += 'Quarters: ' + coinArray.quarters + '<br>';
                }
                if (coinArray.dimes > 0) {
                    change += 'Dimes: ' + coinArray.dimes + '<br>';
                }
                if (coinArray.nickels > 0) {
                    change += 'Nickels: ' + coinArray.nickels + '<br>';
                }
                if (coinArray.pennies > 0) {
                    change += 'Pennies: ' + coinArray.pennies + '<br>';
                }
                if (change == '') {
                    change += 'No change returned.'
                }
                document.getElementById('change-box').innerHTML = change;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                var responseText = $.parseJSON(jqXHR.responseText);
                var errorMessage = responseText.message;
                document.getElementById('message-box').innerHTML = errorMessage;
            }
        });
    });

    $('#dollar-btn').click(function () {
        var money = $('#balance').val();
        money = +money + +1.00;
        $('#balance').val(money);
        document.getElementById('balance').innerHTML = parseFloat(Math.round(money * 100) / 100).toFixed(2);
    });

    $('#quarter-btn').click(function () {
        var money = $('#balance').val();
        money = +money + +0.25;
        $('#balance').val(money);
        document.getElementById('balance').innerHTML = parseFloat(Math.round(money * 100) / 100).toFixed(2);
    });

    $('#dime-btn').click(function () {
        var money = $('#balance').val();
        money = +money + +0.10;
        $('#balance').val(money);
        document.getElementById('balance').innerHTML = parseFloat(Math.round(money * 100) / 100).toFixed(2);
    });

    $('#nickel-btn').click(function () {
        var money = $('#balance').val();
        money = +money + +0.05;
        $('#balance').val(money);
        document.getElementById('balance').innerHTML = parseFloat(Math.round(money * 100) / 100).toFixed(2);
    });

    $('#change-btn').on('click', function () {
        var money = $('#balance').val() * 100;
        var quarters = 0;
        var dimes = 0;
        var nickels = 0;
        var pennies = 0;

        while (money > 24) {
            quarters++;
            money -= 25;
        }

        while (money > 9) {
            dimes++;
            money -= 10;
        }

        while (money > 4) {
            nickels++;
            money -= 5;
        }

        while (money > 0) {
            pennies++;
            money -= 1;
        }

        $('#balance').val(money);
        document.getElementById('balance').innerHTML = parseFloat(Math.round(money * 100) / 100).toFixed(2);

        var change = '';
        if (quarters > 0) {
            change += 'Quarters: ' + quarters + '<br>';
        }
        if (dimes > 0) {
            change += 'Dimes: ' + dimes + '<br>';
        }
        if (nickels > 0) {
            change += 'Nickels: ' + nickels + '<br>';
        }
        if (pennies > 0) {
            change += 'Pennies: ' + pennies + '<br>';
        }
        if (change == '') {
            change += 'No change returned.'
        }
        document.getElementById('change-box').innerHTML = change;
    });

});
