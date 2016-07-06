$(document).ready(function($) {

    var onClass = "on";
    var showClass = "show";
    
    function floatLabel(){
    	$(".float-label input").unbind();

    	$(".float-label input").bind("checkval", function() {
		    var label = $(this).prev("label");

		    if (this.value !== "") {
		        label.addClass(showClass);
		    } else {
		        label.removeClass(showClass);
		    }
		}).on("keyup", function() {
		    $(this).trigger("checkval");
		}).on("focus", function() {
		    $(this).prev("label").addClass(onClass);
		    $(this).attr('placeholder', '');
		}).on("blur", function() {
			if(this.value !== "") {
				$(this).addClass('paddingTop30');
			}
			else
			{
				$(this).removeClass('paddingTop30');
				$(this).prev("label").removeClass(onClass);
			    $(this).attr('placeholder', $(this).prev("label").html());
			}
			
		}).trigger("checkval");

		$(".float-label textarea").unbind();

		$(".float-label textarea").bind("checkval", function() {
		    var label = $(this).prev("label");

		    if (this.value !== "") {
		        label.addClass(showClass);
		    } else {
		        label.removeClass(showClass);
		    }
		}).on("keyup", function() {
		    $(this).trigger("checkval");
		}).on("focus", function() {
		    $(this).prev("label").addClass(onClass);
		    $(this).attr('placeholder', '');
		}).on("blur", function() {
			if(this.value !== "") {
				$(this).css('padding-top','20px');
			}
			else
			{
				//$(this).removeClass('paddingTop20');
				$(this).css('padding-top','');
				$(this).prev("label").removeClass(onClass);
			    $(this).attr('placeholder', $(this).prev("label").html());
			}
			
		}).trigger("checkval");
	}

	floatLabel();

	$('.btn-invite a').click(function(e) {
		e.preventDefault();
		$('#invite-textfield').append('<div class="float-label form-group"><label class="">Invite a colleague</label><input class="form-control" type="text" placeholder="Invite a colleague"></div>');
		floatLabel();
	});

	function setSelect (sel) {
		var select = $(sel).find('select');
		var label = $(sel).find('label');

		console.log(label.html(), select.val())

		if (select.val()) {
			if ($(sel).find('.bootstrap-select').hasClass('open')) {
				$(label).addClass(onClass);
				$(sel).find('.bootstrap-select.btn-group .dropdown-toggle .filter-option').addClass('paddingTop20');
			} 
		} else {
			// if select val is null

			// if select is open or close
			if ($(sel).find('.bootstrap-select').hasClass('open')) {
				$(label).addClass(onClass);
				$(sel).find('.bootstrap-select.btn-group .dropdown-toggle .filter-option').addClass('paddingTop20');

				// if select val is equal to the label
				if (select.val() == label.html() || select.val() == null) {
					$(sel).find('.filter-option').html('');
				}
			} else {
				$(label).removeClass(onClass);
				$(sel).find('.bootstrap-select.btn-group .dropdown-toggle .filter-option').removeClass('paddingTop20');

				// if select val is equal to the label
				if (select.val() == label.html() || select.val() == null) {
					$(sel).find('.filter-option').html(label.html());
				}
			}

		}

		// if (select.val())
	}

	var currSel;

	$(".select-picker.float-label").click(function (event) {
		currSel = this;
		setTimeout(function() {
			setSelect(currSel);
		}, 50)

		$(window).unbind().click(function (e) {
			setSelect(currSel); 
		})
	})
});