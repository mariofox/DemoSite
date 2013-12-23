$(function(){
    // Set up basic options for the cart fancybox
    var fancyCartOptions = {
        maxWidth    : 940,
        maxHeight   : 570,
        fitToView   : false,
        width       : '100%',
        height      : '100%',
        autoSize    : true,
        closeClick  : false,
        topRatio    : 0,
        openEffect  : 'none',
        closeEffect : 'none',
        type        : 'ajax',
        padding     : 5
    };
    
    var fancyProductOptionsOptions = {
        maxWidth    : 180,
        fitToView   : false,
        width       : '100%',
        height      : '100%',
        autoSize    : true,
        closeClick  : false,
        openEffect  : 'none',
        closeEffect : 'none'
    };
    
    // This will change the header "X item(s)" text to the new count and pluralization of "item"
    function updateHeaderCartItemsCount(newCount) {

        //Pull the word that was set in the html from the internationalized version from the locale
        var singularItem = $('span#headerCartItemWordSingular_i18n').text();
        var plurarlItem = $('span#headerCartItemWordPlural_i18n').text();

        $('.headerCartItemsCount').html(newCount);
        $('.headerCartItemsCountWord').html((newCount == 1) ? singularItem: plurarlItem);
    }
    
    function updateWithPromo(promo) {
        $('.headerCartItemCount').html();
    }
    
    // Hides the add to cart/add to wishlist button and shows the in cart/in wishlist button
    // orderType can either be 'cart' or 'wishlist'
    function showInCartButton(productId, orderType) {
        $('.productActions' + productId).children('.in_'+orderType).removeClass('hidden');
        $('.productActions' + productId).children('.add_to_'+orderType).addClass('hidden');
    }
    
    // Hides the in cart/in wishlist button and shows the add to cart/add to wishlist button
    // orderType can either be 'cart' or 'wishlist'
    function showAddToCartButton(productId, orderType) {
        $('.productActions' + productId).children('.add_to_'+orderType).removeClass('hidden');
        $('.productActions' + productId).children('.in_'+orderType).addClass('hidden');
    }
    
    // Actualiza valor total del producto y valor total de la orden en el fancybox de orden
    function updatePricesProductOrder(orderItemId, productTotalPrice, orderTotalPrice) {
    	$('#totalValue' + orderItemId).html('$ ' + productTotalPrice);
    	$('#subtotal').html('$ ' + orderTotalPrice);
    }

    // Show the cart in a modal when any link with the class "fancycart" is clicked
    $('body').on('click', 'a.fancycart', function() {
        var extendedOptions = $.extend({ href : $(this).attr('href') }, fancyCartOptions);
        
        if ($(this).hasClass('refreshonclose')) {
            extendedOptions = $.extend({ afterClose: function() { window.location.reload(); }}, extendedOptions);
        }
        
        $.fancybox.open(extendedOptions);
        return false;
    });
    
    // Intercept add to cart operations and perform them via AJAX instead
    // This will trigger on any input with class "addToCart" or "addToWishlist"
    $('body').on('click', 'input.addToCart,input.addToWishlist', function() {
        var $button = $(this),
            $container = $button.closest('.product_container'),
            $form = $button.closest('form'),
            $options = $container.find('span.option-value'),
            $errorSpan = $container.find('span.error');
            $productOptionsSpan = $container.find('span.productOptionsSpan');
        if ($container.length == 0) {
            var myId = $button.parent().attr('id').substring('productOptions'.length);
            $container = $('.productActions' + myId).closest('.product_container');
            $form = $container.find('form');
            $options = $button.parent().find('span.option-value');
            $errorSpan = $button.parent().find('span.error');
            $productOptionsSpan = $container.find('span.productOptionsSpan');
        }
        
        var itemRequest = BLC.serializeObject($form),
            modalClick = $button.parents('.fancybox-inner').length > 0,
            wishlistAdd = $button.hasClass('addToWishlist');
            
        if (itemRequest.hasProductOptions == "true" && !modalClick) {
            $.fancybox.open($.extend({ href : '#productOptions' + itemRequest.productId}, fancyProductOptionsOptions));
        } else {
            $options.each(function(index, element) {
            	var optionType = $(element).data('optiontype');
            	var value;
            	
            	if ("TEXT" == optionType) {
            		value = $(element).next().find('input').val();
            	} else {
            		value = $(element).text();
            	}//need to add other types(date,long, etc) as needed
            	
            	
            	itemRequest['itemAttributes[' + $(element).attr('id') + ']'] = value;
            });
            
            BLC.ajax({url: $form.attr('action'), 
                    type: "POST",
                    dataType: "json",
                    data: itemRequest
                }, function(data, extraData) {
                    if (data.error) {
                        if (data.error == 'allOptionsRequired') {
                            $errorSpan.css('display', 'block');
                            $errorSpan.effect('highlight', {}, 1000);
                        } else if (data.error == 'productOptionValidationError') {
                        	// find the product option that failed validation with jquery
                        	// put a message next to that text box with value = data.message
                        	$productOptionsSpan.text('Error validando el producto: '+ data.errorCode+' '+data.errorMessage);
                        	$productOptionsSpan.css('display', 'block');
                        	$productOptionsSpan.effect('highlight', {}, 1000);
                        	
                        } else if (data.error == 'BasicInventoryUnavailable') {
                        	HC.showNotification("No hay cantidades disponibles de este producto");
                        
                        } else {
                            HC.showNotification("Error adicionando al carrito");
                        }
                    } else {
                        $errorSpan.css('display', 'none'); 
                        $productOptionsSpan.css('display', 'none'); 
                        updateHeaderCartItemsCount(data.cartItemCount);
                        
                        if (modalClick) {
                            $.fancybox.close();
                        } else if (wishlistAdd) {
                            showInCartButton(data.productId, 'wishlist');
                        } else {
                            showInCartButton(data.productId, 'cart');
                        }
                        
                        if (wishlistAdd) {
                            HC.showNotification("¡" + data.productName + " ha sido adicionado a su lista de deseos!");
                        } else {
                            HC.showNotification("¡" + data.productName + " ha sido adicionado al carrito!", 2000);
                        }
                    }
                }
            );
        }
        return false;
    });

    // Intercept update quantity operations and perform them via AJAX instead
    // This will trigger on any input with class "updateQuantity"
    $('body').on('click', 'input.updateQuantity', function() {
        var $form = $(this).closest('form');
        $('.errorInventory' + $form.children('input[name="skuId"]').val() ).hide();
        $('.errorInventory' + $form.children('input[name="skuId"]').val() ).removeClass('errInvActive');
        BLC.ajax({url: $form.attr('action'),
                type: "POST", 
                dataType: "json",
                data: $form.serialize() 
            }, function(data, extraData) {
            	if(!data.error) {
            		updatePricesProductOrder(data.orderItemId, data.productTotalPrice, data.orderTotalPrice);
            		if (extraData) {
                        updateHeaderCartItemsCount(extraData.cartItemCount);
                        if ($form.children('input.quantityInput').val() == 0) {
                            showAddToCartButton(extraData.skuId, 'cart');
                        }
                    }
            	} else if ( data.error == 'BasicInventoryUnavailable' ) {
            		$('.errInvJS.errorInventory' + data.skuId).show();
            		$('.errInvJS.errorInventory' + data.skuId).html('Sólo hay ' + data.errorInventoryQuantityAvailable + ' unidades de este producto');
            		$('.errorInventory' + data.skuId).addClass('errInvActive');
            		
            	}
                

                //$('.fancybox-inner').html(data);
            }
        );
        return false;
    });
    
    // Verificar que no haya errores de inventario cuando elige Comprar (Checkout)
    $('body').on('click', 'a.checkoutButton', function() {
    	if( $('.errInvActive').val() != undefined ) {
        	alert("Por favor verifique los productos sin inventario y modifique las cantidades");
        	return false;
        }
    });
    
    // Intercept remove from cart operations and perform them via AJAX instead
    // This will trigger on any link with class "remove_from_cart"
    $('body').on('click', 'a.remove_from_cart', function() {
        var link = this;
        
        BLC.ajax({url: $(link).attr('href'),
                type: "GET"
            }, function(data, extraData) {
                updateHeaderCartItemsCount(extraData.cartItemCount);
                showAddToCartButton(extraData.productId, 'cart');
                
                $('.fancybox-inner').html(data);
            }
        );
        return false;
    });
    
    // Intercept remove from cart operations and perform them via AJAX instead
    // This will trigger on any link with class "remove_from_cart"
    $('body').on('click', 'a.remove_promo', function() {
        var link = this;
        
        BLC.ajax({url: $(link).attr('href'),
                type: "GET"
            }, function(data) {
                $('.fancybox-inner').html(data);
            }
        );
        return false;
    });
    
    $('body').on('click', 'input#addPromo', function() {
        var $form = $(this).closest('form');
        
        BLC.ajax({url: $form.attr('action'),
                type: "POST", 
                data: $form.serialize() 
            }, function(data, extraData) {
                if(!extraData.promoAdded) {
                    $("#cart_promo_error").html("La promoción no se pudo aplicar: " + extraData.exception).css("display", "");
                } else {
                    $('.fancybox-inner').html(data);
                }
            }
        );
        return false;
    });
});