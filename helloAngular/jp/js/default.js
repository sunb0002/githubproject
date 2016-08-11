var func = {};

function returnFunc() {
    var active = [];
    var inactive = [];

    $.each(func, function(cap, onOff) {
        if (onOff) {
            active.push(cap);
        } else {
            inactive.push(cap);
        }
    });

    console.log('Active =', active)
    console.log('Inactive =', inactive);
}

function preloadSK() {
   setTimeout(function () {
       imagesLoaded(document.querySelector('body'), function(instance) {
           $('#side-kick-nav').css('visibility', 'visible');
       });
   },500)
}


$(document).ready(function($) {

    $('html').addClass($.browser.name);

    func._BBC = isThereBtnBreadcrumb(), // Button Breadcrumb
    func._SBC = isThereStBreadcrumb(), // Standard Breadcrumb
    func._SK = isThereSideKickNav(), // Sidekick Nav
    func._BS = isThereBackstretch(), // Backstretch
    func._TBS = isThereTileBackstretch(), // Tile Backstretch
    func._TTL = isThereTwoTileLinks(), // Tile Backstretch
    func._BX = isThereBxSlider(), // BxSlider
    func._DDD = isThereDotDotDot(), // DotDotDot (Ellipsis tool)
    func._BLX = isThereBlendExtend(), // Blend Extend - For Hero Banner
    func._FIL = isThereFilter(), // Filter UI
    func._FILC = isThereFilterComp(), // Filter UI Component
    func._SLD = isThereSlider(), // Slider UI
    func._SP = isThereSelectPicker(), // Select Dropdown UI
    func._ACTF = isThereAutoCompleteTextfield(), // AutoComplete UI
    func._IPM = isThereInputMask(), // Inputmask JS
    func._CLD = isThereCalender(), // Calender Bootstrap JS
    func._JVA = isThereJVerticalAlign(), // Vertical Align using Jquery
    func._EHT = isThereEqualHeightColumn(), // Equal Height Column
    func._TAG = isThereShowMoreShowLessTags(), // Show More Show Less Tags
    func._SMI = isThereSocialMediaIcon(), // Social Media Icons
    func._FAC = isThereFooterAccordion(), // Footer Accordion for Mobile
    func._BHT = isThereBlogHeight(), // Blog Height
    func._GM = isThereGoogleMap(), // Google Map
    func._TTS = isThereTestimonialSlider(), // Testimonial Slider
    func._VB = isThereVerticalBorder(), // Vertical Border
    func._SPP = isThereSpeakerPanel(), // Speaker Panel
    func._DA = isThereDetailsAgenda(), // Resource Events Details Agenda
    func._TVS = isThereTVSelection(), // TV Selection
    func._SPR = isThereSplitRow(), // TV Selection
    func._NUM = isThereNumberOnly(), // Number Only
    func._CP = isThereChangePhone(), // Change Phone
    func._MBX = isThereMobileOnlyBxslider(), // Change Phone
    func._CTBX = isThereChangeTextBxslider(), // Change Text BxSlider
    func._ACC = isThereAccordion(), // Accordion Widget
    func._SUP = isThereSupport(), // Support Widget
    func._GAL = isThereGallery(), // Gallery Widget
    func._OLI = isThereOrderedList(), // Order List
    func._IMP = isThereImportantMessage(), // Important Message
    func._FUL = isThereFull(), // Full Width Image
    func._PLI = isTherePersonalLanding(), // Personal Landing Isotope
    func._PLA = isTherePlanDetails(), // View Plan Details
    func._DABA = isThereDataBinding(), // DataBinding
    func._LOG = isThereLoginMenu(), // User Login
    func._VP = isThereVideoPanel(), // Video Panel
    func._VSLIDE = isThereVideoSlide(), // Video Slide
    func._SCROLL = isThereFreakingScroll(), // Init Scroll
    func._POPLINK = isThereLink(), // Init populate links function
    func._GREYBG = isThereGreyBG, // Init mobileGreyBG
    func._localhost = document.location.hostname == "localhost" ? true : false, // test for localhost
    func._staging = document.location.hostname == "www.decision-science.com" ? true : false, // test for staging
    func._windowWidth = $(window).width(),
    func._forceResize = false;

    if (func._BBC) {
        var bcLevels = $('.btn-breadcrumb .btn-dropdown'),
            bcSelectDD = bcLevels.find('select'),
            bcTotalLevels = bcLevels.length;

        function setDD() {
            for (var i = 0; i < bcTotalLevels; i++) {
                var curBcLevel = $(bcLevels[i]),
                    nextBcLevel = $(bcLevels[i + 1]),
                    curBcLevelVal = curBcLevel.find('select:not(:hidden) option:selected').val(),
                    nextBcLevelDD = nextBcLevel.find('select');

                nextBcLevelDD.addClass('hidden');

                var nextBcLevelSel = $(nextBcLevel).find('[name="' + curBcLevelVal + '"]');

                if (nextBcLevelSel.length != 0) nextBcLevelSel.removeClass('hidden');
                if (curBcLevel.find('select.hidden').length == curBcLevel.find('select').length) {
                    curBcLevel.addClass('hidden');
                } else {
                    curBcLevel.removeClass('hidden');
                }
            };
        }

        function SK_to_BBC(val, nm) {
            $(bcSelectDD).parent().find('[name="' + nm + '"]').val(val).change();
        }

        setDD();

        bcSelectDD.change(function(event) {
            setDD()
        });
    } else if (func._SBC) {
        var sbc = $('.st-breadcrumb .breadcrumb');

        function SK_to_SBC(val, nm) {
            var _skNavLevelVis = $('#side-kick-nav .level:visible');

            var _SBC_array = [];

            for (var l = 0; l < _skNavLevelVis.length; l++) {
                var _skNavLevelVisAct = $(_skNavLevelVis[l]).find('.active a');
                if (_skNavLevelVisAct.length != 0) {
                    _SBC_array.push(_skNavLevelVisAct.attr('child-nav').replace('#', ''));
                }
            };

            //sbc.html('');

            for (var m = 0; m < _SBC_array.length; m++) {
                // sbc.append('<li><a href="#">' + _SBC_array[m] + '</a></li>')
            };
        }
    };

    if (func._SK) {
        $('body').addClass('side-kick-on');

        var skNav = $('#side-kick-nav'),
            skFill = skNav.find('.side-fill'),
            skLevels = skNav.find('.level'),
            skLevelsNot1st = skNav.find('.level:not(:first)'),
            mainToggle = $('#main-toggle'),
            skBackBtnWrapper = $('.back-btn-wrapper'),
            skBackBtn = skBackBtnWrapper.find('.back-btn'),
            skLevelHeight = 0,
            resizeIdSK,
            evt;

        function initSkDimension() {
            // Desktop
            for (var i = 0; i < skLevels.length; i++) {
                var s = $(skLevels[i]).find('ul');
                for (var j = 0; j < s.length; j++) {
                    if (skLevelHeight < $(s[j]).height())
                        skLevelHeight = $(s[j]).height();
                };
            };

            skLevels.find('ul').height(skLevelHeight);
            skLevels.addClass('hidden');

            setTimeout(function() {
                var body_height = $('body').height() - $('.main-nav').height() - $('#global').outerHeight() - 20
                skFill.height(body_height + 40);
                skLevels.find('ul').height(body_height);
                preloadSK();
            }, 250);
        }

        function setSkLevel() {
            skNav.toggleClass('hidden');
            $('body').toggleClass('side-kick-on');
            mainToggle.toggleClass('active');

            skNav.find('.level').addClass('hidden');
            skNav.find('.level li').removeClass('active');
            skNav.find('.overlay').removeClass('lvl1 lvl2 lvl3');
            $(skLevels[0]).removeClass('hidden');
            skNav.removeClass('curLevel2 curLevel3');
            skNav.addClass('curlevel1');
        }

        function listenToResizeSK() {
            initSkDimension();
            initSKAction();
            $(skLevels[0]).removeClass('hidden');
        }

        function setSK(curSK) {
            if ($(this).find('a').length != 0) {
                curSK = $(this);
            }

            var nextSkLevel, nextSkLevelUl, nextSkLevelInd,
                curSkLevel, curSkSel, curSkLevelInd, curSkLevelUlName,
                collapse = false,
                curSkLevel = $(curSK).parent('[name]'),
                // curSkLevel.find('li').removeClass('active').addClass('inactive');
                curSkSel = $(curSK).find('a').attr('child-nav').replace('#', ''),
                curSkLevelInd = $(curSK).parents('.level').index(),
                curSkLevelUlName = curSkLevel.attr('name');

            // nextSkLevelUl        = $('.level ul[name="' + curSkSel + '"]');
            nextSkLevelUl = curSkLevel.parent('.level').next('.level').find('ul[name="' + curSkSel + '"]')
            nextSkLevelAllUl = curSkLevel.parent('.level').next('.level').find('ul');

            nextSkLevelAllUl.find('li').removeClass('active');

            for (var p = curSkLevelInd + 1; p < skLevels.length; p++) {
                $(skLevels[p]).addClass('hidden');
            };

            // next Side Kick level
            nextSkLevel = nextSkLevelUl.parent('.level');
            nextSkLevel.removeClass('hidden');

            // hide all UL in the next Side Kick level
            nextSkLevel.find('ul').addClass('hidden');
            // nextSkLevel.addClass('active');
            // show next Side Kick UL in next side kick level
            nextSkLevelUl.removeClass('hidden');

            // remove inactive class in selected option and add active class
            curSkLevel.find('li').removeClass('active');
            $(curSK).addClass('active');

            // if need to collapse (only on click), then reset all levels, remove class minified
            if (collapse)
                skLevels.removeClass('minified');

            // find number of levels that is active + visible
            var visSkLevel = skLevels.find('.active:visible').length;

            $(skNav).attr('class', 'curLevel' + $(skNav).find('.level:visible').length);

            if (visSkLevel > 1) {
                for (var j = 0; j < visSkLevel; j++) {
                    if (collapse) {
                        $(skLevels[j - 1]).addClass('minified');
                    }
                };
            } else {

            }

            var skFillId = 0;
            skLevels.find('.overlay').attr('class', 'overlay');

            function setOverlay(skLevelInd) {
                for (var k = 0; k < skLevelInd; k++) {
                    $(skLevels[k]).find('.overlay').addClass('lvl' + (skLevelInd - k));
                };
                skFill.find('.overlay').attr('class', 'overlay').addClass($(skLevels[0]).find('.overlay').attr('class').replace('overlay ', ''));
            }

            setOverlay(curSkLevelInd);

            skLevels.find('.overlay').unbind().mouseover(function(event) {
                $(this).attr('class', 'overlay');
                var moSkLevelInd = $(this).parents('.level').index();
                skLevels.find('.overlay').attr('class', 'overlay');
                setOverlay(moSkLevelInd);
            });

            if (func._BBC) {
                SK_to_BBC(curSkSel, curSkLevelUlName);
            } else if (func._SBC) {
                SK_to_SBC(curSkSel, curSkLevelUlName);
            }

            if (nextSkLevel.length == 0) {
                // if (curSK.find('a').attr('href') != undefined) {
                    // console.log(evt);
                // }
            } else {
                evt.preventDefault();
            }

            // console.log(nextSkLevel, nextSkLevelUl, nextSkLevelInd);
        }

        function listenToBody() {
            setTimeout(function() {
                $("body").click(function(e) {
                    if (!$('#main-toggle, .icon-bar').is(e.target) && $(window).width() > 992) {
                        setSkLevel();
                        $("body").unbind();
                    }
                });
            }, 500)
        }

        function initSKAction() {

            var curLevel = Number(skNav.attr('curlevel'));
            var hiddenClass = skNav.hasClass('hidden') ? 'hidden' : '';

            if (isNaN(curLevel)) {
                curLevel = 1;
                skNav.attr('class', 'curlevel' + 1 + ' ' + hiddenClass);
            }

            skLevels.find('li:not(".header,.divider")').unbind().unbind("mouseenter").unbind("mouseleave");;
            skLevels.find('li:not(".header,.divider")').removeProp('hoverIntent_t');
            skLevels.find('li:not(".header,.divider")').removeProp('hoverIntent_s');

            if ($(window).width() > 767) {
                skLevels.find('li:not(".header,.divider")').hoverIntent({
                    over: setSK,
                    out: setSK,
                    interval: 35
                })
            } else {
                skLevels.find('li:not(".header")').click(function(event) {
                    evt = event;
                    // ev.preventDefault();
                    setSK($(this))
                });
            }

            skBackBtn.unbind().click(function(event) {
                var curLevel = skNav.attr('class');
                curLevel = Number(curLevel.replace('curLevel', ''));
                curLevel--;

                // console.log(curLevel);

                skNav.attr('class', 'curLevel' + curLevel);

                setTimeout(function() {
                    skNav.find('.level').addClass('hidden');
                    for (var i = 0; i < curLevel; i++) {
                        $(skNav.find('.level')[i]).removeClass('hidden');
                    };
                }, 300)
            });

            mainToggle.unbind().click(function(event) {
                listenToBody();
                setSkLevel();

                // skNav.find('.side, .side-kick').css({right: '50px', opacity: 0});
                // skNav.find('.side, .side-kick').stop(true).animate(
                //  { right: '0px', opacity: 1 },
                //  { easing: 'easeOutQuint',duration:400 }
                // );
            });

            // skLevels.find('li:not(".header")').hover(function (event) { setSK($(this), false) });

            for (var k = 0; k < curLevel; k++) {
                $(skNav.find('.level')[k]).removeClass('hidden');
            };
        }

        initSKAction();
        initSkDimension();
        setSkLevel();
    };

    if (func._BLX) {
        // find the parent text reference
        var blendExtend = $('.blend-extend');

        $.each(blendExtend, function(j, blend) {
            var hero = $(blend).parent().find('.hero-absolute');

            var hero_i = hero.find('i')
            var hero_text = hero_i.html().split('<br>');
            var hero_span = [];

            $.each(hero_text, function(i, word) {
                hero_span.push('<span>' + hero_text[i] + '</span>')
                if (i < hero_text.length - 1) {
                    hero_span.push('<br>');
                }
            });

            hero_i.html(hero_span.join(' ').replace(/\r?\n|\r/g, ''));

            if (hero_span.length == 1) {
                $($(blend).find('.ex span')[1]).remove();
                $(blend).find('.ex br').remove();
            }

            $.each($(blend).find('.extend span'), function(j, bl) {
                // $(bl).css('left',-$(bl).width());
            });
        });

        // BlendExtend is part of the masthead and potentially can contain contents
        // means .main-graphics exists
        var mainGraphic = $('.main-graphic');

        function setMastheadContentHeight() {
            $.each(mainGraphic, function(j, mg) {

                if ($(mg).find('.bxslider').length == -1) {
                    var contentMaster = $(mg).find('.mastheadContent-master');
                    var contentClone = $(mg).find('.mastheadContent');

                    if (contentClone.length > 0) {
                        contentClone.height(contentMaster.outerHeight());
                    }

                } else {
                    var bxli = $(mg).find(".bxslider>li");

                    $.each(bxli, function(k, bxli) {

                        var prevCss = $(bxli).attr('style');

                        $(bxli).css({
                            position: 'absolute',
                            visibility: 'hidden',
                            display: 'block'
                        })

                        var contentMaster = $(bxli).find('.mastheadContent-master');
                        var contentClone = $(bxli).find('.mastheadContent');

                        if (contentClone.length > 0) {
                            contentClone.height(contentMaster.outerHeight());
                        }

                        $(bxli).attr("style", prevCss ? prevCss : "");
                    });
                }
            });
        }

        setTimeout(function() {
            setMastheadContentHeight()
        }, 250);

        function listenToResizeBLX() {
            setMastheadContentHeight()
        }
    };

    if (func._BS) {
        var bs = $('.backstretch');

        var bxArray = [];

        // Only for Mobile
        function alignMastHeadDiagonals(bs) {
            // for mobile diagonal
            var mb = $(bs).parents('.mobile-masthead:visible');
            var dia = mb.parent().find('.diagonal:visible');
            var pgr = mb.parent().find('.bx-controls:visible');

            if (mb.length > 0) {
                dia.css('top', $(mb).find('.bs-src').height())
                pgr.css('top', $(mb).find('.bs-src').height() + 30)
            }
        }

        // Remove br tags in mobile
        function removeBreakMobile() {
            if ($(window).width() <= 768) {
                // console.log("mobile remove br")
                $('.mobile-masthead').find('.masthead-title br').remove();
            }
        }

        $.each(bs, function(i, _bs) {
            // if it is a bxslider
            if ($(_bs).find('.bxslider').length > 0) {
                // bxslider with 1 slide will be a masthead
                // consist of 2 portion
                // desktop_bs   : constains assets for desktop
                // mobile_bs    : contains assets for mobile

                var mobile_bs = $(_bs).find('.mobile-masthead');
                var desktop_bs = $(_bs).find('.desktop-masthead');

                var desktop_bx = $(desktop_bs).find('.bxslider');
                var mobile_bx = $(mobile_bs).find('.bxslider');

                var desktop_li = desktop_bx.children('li');
                var mobile_li = mobile_bx.children('li');

                var pgr = desktop_bx.attr('pager') == 'true' ? true : false;
                var ato = desktop_bx.attr('auto') == 'true' ? true : false;

                if (desktop_li.length == 1) {
                    pgr = false;
                }

                $.each(desktop_li, function(j, _li) {
                    var img = $(_li).find('.bs-src');
                    if (img.attr('src') != undefined && img.attr('src') != '') {
                        $(_li).backstretch(img.attr('src')
                            // ,{centeredX: false}
                        )
                    }
                    img.hide();
                });

                $.each(mobile_li, function(j, _li) {
                    var img = $(_li).find('.bs-src');
                    if (img.attr('src') != undefined && img.attr('src') != '') {
                        $(_li).find('.mobile-masthead-img').backstretch(img.attr('src')
                            // ,{centeredY: false}
                        )
                    }
                    img.hide();
                });

                var opt = {
                    preventDefaultSwipeY: false,
                    pager: pgr,
                    controls: false,
                    auto: ato,
                    mode: 'fade',
                    onSlideBefore: function() {
                        setMastheadContentHeight();
                    }
                }

                var bxSD = desktop_bx.bxSlider(opt);
                var bxSM = mobile_bx.bxSlider(opt);

                bxArray.push(bxSD);
                bxArray.push(bxSM);

                alignMastHeadDiagonals(bxSM);
                removeBreakMobile();
                // if it is not bxslider
            } else if ($(_bs).find('.bxslider').length == 0) {
                var img = $(_bs).find('.bs-src');
                var src = img.attr('src');
                if (img.attr('src') != undefined) {
                    $(_bs).backstretch(src);
                }
                img.hide();
            }
        });

        setTimeout(function () {
            reAlignMastheadImg();
        },250)

        function listenToResizeBS() {
            var totalReset = 30;
            var curReset = 0;
            var listenToResizeTimer;

            $.each(bxArray, function(i, bs) {

                if ($(bs).parents('.desktop-masthead').length > 0) {
                    var msd = $(bs).parents('.desktop-masthead');
                    var li = msd.find('li')

                    $.each(li, function(j, _li) {
                        if ($(_li).find('.backstretch').length > 0) {
                            var bsSrc = $(_li).find('.bs-src').attr('src');
                            $(_li).find('.backstretch').remove();
                            if (bsSrc != undefined) {
                                $(_li). backstretch(bsSrc);
                            }
                        }
                    });
                }

                bs.reloadSlider();
                alignMastHeadDiagonals(bs);
                removeBreakMobile();

                setTimeout(function () {
                    reAlignMastheadImg();
                },250)
            });
        }

        function reAlignMastheadImg () {
            var safe_width_desktop = 890;
            var safe_height_mobile = 300;

            $.each(bs, function(i, _bs) {
                $.each($(_bs).find('.desktop-masthead li .backstretch img'), function(j, img) {
                    alignImg(img, 'desktop');
                });
                $.each($(_bs).find('.mobile-masthead li .mobile-masthead-img .bs-src'), function(j, img) {
                    alignImg(img, 'mobile');
                });
            });

            function alignImg (img, myscreen) {
                var img = $(img)[0];
                var real_width, real_height,
                    cur_width, cur_height,
                    scale_img, scaled_safe,
                    xtraImgPerSide, xtraSafePerSide;

                cur_width = $(img).width();
                cur_height = $(img).height();

                $("<img/>").attr("src", $(img).attr("src"))
                    .unbind()
                    .load(function() {
                        real_width      = this.width;
                        real_height     = this.height;
                        scale_img       = cur_width / real_width;
                        scaled_safe     = safe_width_desktop * scale_img;
                        xtraImgPerSide  = (cur_width - $(window).width()) / 2;
                        xtraSafePerSide = ((scaled_safe - $(window).width()) / 2) < 0 ? 0 : ((scaled_safe - $(window).width()) / 2);
                        $(img).css('left', - (xtraImgPerSide + xtraSafePerSide));
                    });
            }
        }
    };

    if (func._DDD) {
        // truncate using clamp.js
        var dotdotdot = $(".dotdotdot");
        $.each(dotdotdot, function(i, ddd) {
            $('.dotdotdot').dotdotdot({
                watch: "window"
            });
        });
    };

    if (func._FIL) {
        var filtersGrp = $('.filters');

        $.each(filtersGrp, function(i, fil) {
            $(fil).find('a').click(function(e) {
                e.preventDefault();
                $(this).parent('li').toggleClass('active');
            });
        });
    };

    if (func._FILC) {
        var filter_component = $('.filter-component');
        var filter_catClass = 'filter-cat-grp';
        var filter_optClass = 'filter-opt';

        function listenToResizeFILC(argument) {
            $.each(filter_component, function(i, filc) {

                var filc_checkbox = $(filc).find('.filter-target .checkbox');
                filc_checkbox.removeClass('twoliner');

                $.each(filc_checkbox, function(j, fc) {
                    if ($(fc).outerHeight() > 42) {
                        $(fc).addClass('twoliner')
                    }
                });

                // $(filc).find('.filter-wrapper').height(0);
                $(filc).find('.plus-minus').html('+');
                $(filc).find('.filter-wrapper').removeAttr('style');
                $(filc).find('.filter-wrapper').attr('ht', $(filc).find('.filter-wrapper').outerHeight())
                $(filc).find('.filter-wrapper').height(0);

            });
        }

        function initFilc() {
            $.each(filter_component, function(i, filc) {
                var filc_hideShow = $(filc).find('.btn-filter'),
                    filc_cont = $(filc).find('.filter-cont'),
                    filc_cat = $(filc).find('.category'),
                    filc_btn,
                    filc_selAll = $(filc).find('.select-all'),
                    filc_pc = 10,
                    filc_target = $(filc).find('.filter-target'),
                    filc_wrapper = $(filc).find('.filter-wrapper'),
                    filc_totalCol;

                filc_hideShow.click(function(e) {
                    e.preventDefault();
                    if ($(this).find('.plus-minus').html() == "+") {
                        $(this).find('.plus-minus').html('-');
                        filc_wrapper.height(filc_wrapper.attr('ht'));
                    } else {
                        $(this).find('.plus-minus').html('+');
                        filc_wrapper.height(0);
                    }
                    listenToResizeSK();
                    $('.filter-component .filter-count').toggleClass('inactive');
                });

                filc_selAll.click(function(e) {
                    $(filc_target).find('input[type="checkbox"]').prop('checked', $(this).prop('checked'))
                    toggleCheckbox($(filc_target).find('.checkbox'));
                });

                $(filc_cont).find('.checkbox').click(function(e) {
                    toggleCheckbox(this);
                });

                function toggleCheckbox(c) {
                    if ($(c).find('input').is(':checked')) {
                        $(c).addClass('on');
                    } else {
                        $(c).removeClass('on')
                    }

                    checkboxStatus();
                }

                function checkboxStatus() {
                    if ($(filc_target).find('input[type="checkbox"]:not(:checked)').length == 0) {
                        filc_selAll.prop('checked', true);
                    } else {
                        filc_selAll.prop('checked', false);
                    }
                }

                setTimeout(function() {
                    $.each(filc_cat, function(m, fc) {

                        filc_btn = $(fc).find('.checkbox');

                        for (var l = 0; l < filc_btn.length; l++) {
                            if ($(filc_btn[l]).outerHeight() > 42) {
                                $(filc_btn[l]).addClass('twoliner');
                            }

                            if ($(filc_btn[l]).find('input[type="checkbox"]').attr('checked')) {
                                $(filc_btn[l]).addClass('on');
                            }
                        };

                        var curr_slot = 0;
                        var curr_col = 1;
                        var cat_name = $(fc).attr('catName') != undefined ? $(fc).attr('catName') : '';

                        filc_target.append('<div class="' + filter_catClass + ' cat' + (m + 1) + '"><div class="row"></div></div>');

                        var curr_cat;

                        for (var k = 0; k < filc_btn.length; k++) {
                            curr_slot++

                            if ($(filc_btn[k]).hasClass('twoliner')) {
                                curr_slot++;
                            }

                            if (curr_slot > (curr_col * filc_pc)) {
                                curr_col++;
                            }

                            curr_cat = filc_target.find('.' + filter_catClass + '.cat' + (m + 1) + ' .row');

                            if (curr_cat.find('.col' + curr_col).length == 0) {
                                curr_cat.append('<div class="' + filter_optClass + ' col' + curr_col + '">');
                            }

                            curr_cat.find('.col' + curr_col).append($(filc_btn[k]));
                        }

                        var cal_col = (curr_col * 6);
                        if (curr_cat != undefined) {
                            //console.log('cccc',curr_cat.parent('.' + filter_catClass), curr_cat);
                            curr_cat.parent('.' + filter_catClass).addClass('col-md-' + cal_col);
                            // curr_cat.parent('.' + filter_catClass).addClass('col-sm-' + (cal_col * 2));
                            curr_cat.find('.' + filter_optClass).addClass('col-md-' + (24 / curr_col))


                            if (cat_name != '') {
                                curr_cat.prepend('<div class="cat-name col-md-24">' + cat_name + '</div>')
                            }
                        }

                    });

                    $('.filter-cont').remove();

                    var colCount = 0,
                        rowCount = 0,
                        colNum = 0;

                    $(filc_target).append('<div class="row filter-target-row"></div>');

                    $.each($('.filter-cat-grp'), function(n, fcg) {

                        colCount += Number($(fcg).attr('class').substr($(fcg).attr('class').indexOf('col-') + 7));

                        if (colCount > 24) {
                            rowCount++;
                            colCount = 0;
                            $(filc_target).append('<div class="row filter-target-row"></div>');
                        }

                        $(fcg).appendTo($(filc_target).find('.row.filter-target-row').eq(rowCount))
                    });

                    var _ht = filc_wrapper.height();

                    filc_wrapper.height(_ht);
                    filc_wrapper.attr('ht', _ht);
                    filc_wrapper.height(0);

                    $(filc).removeClass('initialising');
                }, 250)
            });
        }

        initFilc();
    }

    if (func._SLD) {
        var sliders = $('.slider');

        $.each(sliders, function(i, sliderDiv) {

            var sliderOpt = {},
                _range, _min, _max, _steps, _labels, _tooltip, _tooltipbottom;

            if ($(sliderDiv).hasClass('min')) {
                _range = 'min';
            }

            if ($(sliderDiv).hasClass('max')) {
                _range = 'max';
            }

            if ($(sliderDiv).hasClass('range')) {
                _range = true;
            }

            if ($(sliderDiv).hasClass('tool-tip')) {
                _tooltip = true;

                if ($(sliderDiv).hasClass('tool-tip-bottom')) {
                    _tooltipbottom = true;
                }
            }

            _min = ($(sliderDiv).attr('min') == undefined) ? 0 : Number($(sliderDiv).attr('min'));
            _max = ($(sliderDiv).attr('max') == undefined) ? 100 : Number($(sliderDiv).attr('max'));

            sliderOpt['range'] = _range;
            sliderOpt['min'] = _min;
            sliderOpt['max'] = _max;

            if ($(sliderDiv).attr('steps') != undefined) {
                sliderOpt['step'] = Number($(sliderDiv).attr('steps'));
            }

            if ($(sliderDiv).attr('labels') != undefined) {
                _labels = Number($(sliderDiv).attr('labels'));
            }

            if ($(sliderDiv).attr('value') != undefined) {
                sliderOpt['value'] = Number($(sliderDiv).attr('value'));
            } else if ($(sliderDiv).attr('values') != undefined) {
                sliderOpt['values'] = $.map($(sliderDiv).attr('values').split(','), function(value) {
                    return Number(value);
                });
            }

            $(sliderDiv).slider(sliderOpt).each(function() {
                var opt = $(this).data().uiSlider.options;

                if (_labels != undefined) {
                    var vals = (opt.max - opt.min) / _labels;

                    $(sliderDiv).append($('<div class="labels" />'));

                    for (var i = 0; i <= vals; i++) {
                        var el = $('<label>' + ((i + opt.min) * _labels) + '</label>').css('left', (i / vals * 100) + '%');
                        $(sliderDiv).find('.labels').append(el);
                    }
                }

                if (_tooltip == true) {

                    var val = opt.values == undefined ? opt.value : opt.values;
                    var tt_class = (_tooltipbottom == true) ? 'tool-tip-bottom' : 'tool-tip';
                    var tooltip = '<div class="' + tt_class + '"></div>';

                    $(sliderDiv).find('.ui-slider-handle').append(tooltip);

                    $.each($(sliderDiv).find('.ui-slider-handle'), function(i, handle) {
                        var va = (val[i] != undefined) ? val[i] : val;
                        $(handle).find('.' + tt_class).text(va);
                    });

                    $(sliderDiv).on('slide', function(event, ui) {
                        $(ui.handle).find('.' + tt_class).text(ui.value);
                    });
                }
            });
        });
    };

    if (func._SP) {

        function initSP() {
            if ($(window).width() >= 768) {
                $('.select-picker select').selectpicker({
                    size: 3.5
                });
            } else {
                $('.select-picker select').selectpicker({
                    size: 5
                });
            }

            if( navigator.userAgent.match(/Android/i)
             || navigator.userAgent.match(/webOS/i)
             || navigator.userAgent.match(/iPhone/i)
             || navigator.userAgent.match(/iPad/i)
             || navigator.userAgent.match(/iPod/i)
             || navigator.userAgent.match(/BlackBerry/i)
             || navigator.userAgent.match(/Windows Phone/i)
             ){
                $('.select-picker select').selectpicker('mobile');
                // console.log("damn mobile")
              }
        }

        initSP();
    }

    if (func._IPM) {
        $("input[data-inputmask]").inputmask();
    }

    if (func._CLD) {
        var cal = $('.load-calendar');

        $.each(cal, function(i, calender_container) {
            var _inline = $(calender_container).attr('inline') == 'true' ? true : false;
            $(calender_container).find('.calendar-cont').datetimepicker({
                inline: _inline,
                format: 'dd/MM/YYYY',
                minDate: moment().subtract(1, 'years').calendar()
            })
            $(calender_container).on('dp.change', function(e) {
                var d_a = e.date.format('dddd DD MMMM YYYY').split(' ');
                $(this).find('.display-date .day').html(d_a[0]);
                $(this).find('.display-date .date .date-num').html(d_a[1]);
                $(this).find('.display-date .date .month-name').html(d_a[2]);
                $(this).find('.display-date .date .year-num').html(d_a[3]);
            })
        });
    };

    if (func._ACTF) {
        // dummy tags
        var availableTags = ["ActionScript", "AppleScript", "Asp", "BASIC", "C", "C++", "Clojure", "COBOL", "ColdFusion", "Erlang", "Fortran", "Groovy", "Haskell", "Java", "JavaScript", "Lisp", "Perl", "PHP", "Python", "Ruby", "Scala", "Scheme"];

        $('.textfield-autocomplete').autocomplete({
            source: availableTags
        })
    }

    if (func._JVA) {
        var jvaTimer;
        var jvaArray = [];
        var ld = false;

        jvaTimer = setInterval(function() {

            $.each($('.jVerticalAlign'), function(i, jv) {
                var ht = ($(jv).next('img').height() - $(jv).height()) / 2;

                $(jv).css('padding-top', ht + 'px');

                if (ht > 0) {
                    jvaArray[i] = 'true';
                } else {
                    jvaArray[i] = 'false';
                }
            });

            if (jvaArray.indexOf('false') == -1) {
                clearTimeout(jvaTimer);
            }

        }, 500)

        function listenToResizeJVA() {
            var jva = $('.jVerticalAlign');

            $.each(jva, function(i, jv) {
                $(jv).css('padding-top', ($(jv).parent().height() - $(jv).height()) / 2 + 'px');
            });
        }
    }

    if (func._CTBX) {
        var changeTextSlider = $('.change-text-slider'),
            col_sizes = ['col-lg-', 'col-md-', 'col-sm-', 'col-xs-'],
            bxChTextSlider = [],
            col_size_id = 0;

        $.each(changeTextSlider, function(k, cts) {
            var tbx = $(cts).bxSlider();
            bxChTextSlider.push(tbx);
        });

        function initChTxtBxSlider() {
            if ($(window).width() >= 1200) {
                col_size_id = 0; // test for col-lg- first
            } else if ($(window).width() >= 992 && $(window).width() < 1200) {
                col_size_id = 1; // test for col-md- first
            } else if ($(window).width() >= 768 && $(window).width() < 992) {
                col_size_id = 2; // test for col-sm- first
            } else if ($(window).width() < 768) {
                col_size_id = 3; // test for col-xs- first
            }

            $.each(bxChTextSlider, function(j, slider) {
                var bxSlider,
                    col_per_row,
                    widthPerCol,
                    firstSlide = $($(slider).children()[0]);

                var firstColClass = firstSlide.attr('class');

                for (var i = col_size_id; i < col_sizes.length; i++) {
                    var _id = firstColClass.indexOf(col_sizes[i]);
                    var _idi;

                    if (_id != -1) {
                        _idi = firstColClass.slice(_id + 7);
                        var last = _idi.indexOf(' ') == -1 ? _idi.length : _idi.indexOf(' ');
                        _idi = Number(_idi.slice(0, last));
                        col_per_row = 24 / _idi;
                        break;
                    }
                }

                widthPerCol = ($(slider).parents('.change-text').width() / col_per_row);

                slider.reloadSlider({
                    slideWidth: widthPerCol,
                    minSlides: 1,
                    maxSlides: 4,
                    controls: false,
                    randomStart: false,
                    infiniteLoop: false
                });
            });
        }
    }

    if (func._EHT) {

        function colEqHt() {

            var eqHtContRow = $('.eq-ht-cont').closest('.row');

            function heightEqHt() {
                if ($(window).width() < 768) {
                    if ($('.mobile-only-bxslider').length) {
                        processEqHt();
                    }
                    else {
                        // console.log("remove freaking height")
                        $('.eq-ht-cont').removeAttr('style')
                    }
                } else {
                    processEqHt();
                    // console.log("mobileBxSlider")
                }
            }

            function processEqHt() {
                // console.log('processEqHt');
                $.each(eqHtContRow, function(ind, row) {
                    // $('.eq-ht-cont').removeAttr('style')

                    var eqHtContCol = $(row).find('.eq-ht-cont');
                    // var eqHtContCol = $(row).children();
                    var h = 0;
                    var total_bottom_link = 0;
                    var total_social_media = 0;
                    var total_stn_btn = 0;
                    var total_tstm_btn = 0;
                    var total_promo_btn = 0;
                    var total_green_btn = 0;

                    $.each(eqHtContCol, function(j, eqH) {

                        if ($(eqH).height() > h) {
                            h = $(eqH).height();
                        }

                        if ($(eqH).find('.bottom-link').length > total_bottom_link) {
                            total_bottom_link = $(eqH).find('.bottom-link').length;
                        }

                        if ($(eqH).find('.socialMediaWrapper').length > total_social_media) {
                            total_social_media = $(eqH).find('.socialMediaWrapper').length;
                        }

                        if ($(eqH).find('.btn.btn-pri.btn-stn.btn-sky-blue').length > total_stn_btn) {
                            total_stn_btn = $(eqH).find('.btn.btn-pri.btn-stn.btn-sky-blue').length;
                        }

                        if ($(eqH).find('.tstm-link').length > total_tstm_btn) {
                            total_tstm_btn = $(eqH).find('.tstm-link').length;
                        }

                        if ($(eqH).find('.promo-bottom-link').length > total_bottom_link) {
                            total_promo_btn = $(eqH).find('.promo-bottom-link').length;
                        }

                        if ($(eqH).find('.btn-green').length > total_green_btn) {
                            total_green_btn = $(eqH).find('.btn-green').length;
                        }
                    });

                    if (total_bottom_link == 1 && total_social_media >= 1 && total_stn_btn == 1) {
                        h += 100;
                        // console.log('a');
                    } else if (total_bottom_link == 0 && total_social_media >= 1 && total_stn_btn == 1) {
                        h += 80;
                        // console.log('b');
                    } else if (total_bottom_link == 1 && total_social_media == 0 && total_stn_btn == 1) {
                        h += 60;
                        // console.log('c');
                    } else if (total_bottom_link == 1 && total_social_media >= 1 && total_stn_btn == 0) {
                        h += 100;
                        // console.log('d');
                    } else if (total_bottom_link == 0 && total_social_media == 0 && total_stn_btn == 1) {
                        h += 75;
                        // console.log('e');
                    } else if (total_bottom_link == 1 && total_social_media == 0 && total_stn_btn == 0) {
                        h += 90;
                        // console.log('f');
                    } else if (total_tstm_btn == 1) {
                        h += 50;
                        // console.log('g');
                    } else if (total_promo_btn == 1 && total_social_media >= 1) {
                        h += 70;
                        // console.log('h');
                    } else if (total_promo_btn == 0 && total_social_media >= 1 && total_bottom_link == 0) {
                        h += 70;
                        // console.log('i');
                    } else if (total_promo_btn == 1 && total_social_media >= 0 && total_bottom_link == 0 && total_tstm_btn == 0) {
                        h += 100;
                        // console.log('j');
                    } else if (total_promo_btn == 0 && total_social_media >= 1 && total_bottom_link == 0) {
                        h += 70;
                        // console.log('k');
                    } else if (total_promo_btn == 1) {
                        h += 120;
                        // console.log('l');
                    } else if (total_green_btn == 1) {
                        h += 50;
                        // console.log('m');
                    } else {
                        // console.log('n', total_promo_btn, total_social_media, total_bottom_link, total_stn_btn, total_green_btn, total_tstm_btn);
                    }

                    var hta = 0;

                    eqHtContCol.height(h);
                });
            }

            $('.eq-ht-cont').removeAttr('style');

            setTimeout(function () {
                heightEqHt();
            }, 300);
        }

        setTimeout(function() {

            if (func._CTBX) {
                initChTxtBxSlider();
            }
            colEqHt();
            if (func._CTBX) {
                initChTxtBxSlider();
            }

            if (func._SPR) {
                rasEqHt();
            }
            // if (func._VID) { vidEqHt(); }
        }, 250)
    }

    if (func._TAG) {
        var dotTags = $('.dotTags');
        dotTags.append('<a class="toggle" href="#"><span class="btn btn-sec btn-green showLess">- Show less</span></a>');

        function createDots() {
            dotTags.dotdotdot({
                after: 'a.toggle',
                watch: "window",
                callback: function() {
                    if ($('.dotTags  a').length > 5) {
                        dotTags.append('<a class="toggle" href="#"><span class="btn btn-sec btn-green showMore">+ Show more</span></a>');
                    }
                }
            });
        }

        function destroyDots() {
            dotTags.trigger('destroy');
        }
        createDots();

        dotTags.on(
            'click',
            'a.toggle',
            function() {
                dotTags.toggleClass('opened');

                if (dotTags.hasClass('opened')) {
                    destroyDots();
                } else {
                    createDots();
                }
                return false;
            }
        );
    }

    if (func._SMI) {
        var smList = $('ul.socialMedia');

        smList.find('.iconBulletContainer').click(function(e) {
            $(this).next().fadeToggle("medium", "linear");
            $(this).toggleClass('on');
        });
    }

    if (func._FAC) {
        $(".site-map-footer #accordion").accordion({
            collapsible: true,
            active: false,
        }).find('.accTitle').click(function(e) {
            e.stopPropagation();
        });
        $(".site-map-footer #accordion .ui-accordion-content").css('height', 'auto');
    }

    if (func._BHT) {
        function blogHt() {
            if ($(window).width() >= 768) {
                $('.featured-product-content').removeAttr('style');
                var featuredRow = $('.blog-featured-product');
                for (i = 0; i < featuredRow.length; i++) {
                    var featuredBlogRow = $(featuredRow[i]).height();
                    var featuredBlogContent = $(featuredRow[i]).find('.featured-product-content').outerHeight();
                    var featuredBlogContentHt = ((featuredBlogRow - featuredBlogContent) / 2) - 3;
                    $(featuredRow[i]).find('.featured-product-content').css('margin-top', featuredBlogContentHt);
                }
            } else {
                $('.featured-product-content').removeAttr('style');
            }
        }
        blogHt();
    }

    if (func._GM) {
        function initialize() {
            var latlng = new google.maps.LatLng(1.306040, 103.772947);
            var myOptions = {
                zoom: 15,
                center: latlng,
                disableDefaultUI: true,
                scrollwheel: false,
                navigationControl: false,
                mapTypeControl: false,
                scaleControl: false,
                draggable: false,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

            var marker = new google.maps.Marker({
                position: latlng,
                map: map,
            });
        }
        google.maps.event.addDomListener(window, "load", initialize);
    }

    if (func._VB) {
        $.each($('.vr'), function(i, vr) {
            var h = $(vr).parents('.row').height();
            $(vr).height(h);
        });
    }

    if (func._TTS) {
        var tts = $('.testimonial-slider'),
            tts_bxa = [],
            tts_opt = {
                controls: false,
                pager: true,
                preventDefaultSwipeX: true
            },
            resizeTTS;

        var bxTest;

        $.each(tts, function(i, _tts) {

            var ttl = $(_tts).find('.testimonial-slider-bx');
            var tts = ttl.bxSlider(tts_opt);

            tts_bxa.push(tts);
        });

        function listenToResizeTTS() {
            $.each(tts_bxa, function(i, _tts) {
                _tts.reloadSlider();
            });
        }
    }

    if (func._SPP) {
        var container = $('.begin-content > .container').outerWidth(),
            contentWidth = $(".begin-content").outerWidth(),
            margin = (contentWidth - container) / 2,
            containerMargin = margin + 100,
            mobile = 0,
            divMax = 0;

        if ($(window).width() > 1440) {
            $(".panel-info").css("left", mobile + "px");
        }
        if ($(window).width() >= 768 && $(window).width() <= 1440) {
            $(".panel-info").css("left", mobile + "px");
        }
        if ($(window).width() < 768) {
            $(".panel-info").css("left", mobile + "px");
        }

        function speakerPanel() {
            $('.panel-link').click(function(e) {
                var wrapperHeight = 372;
                var containerInfoHeight = $(this).next('.panel-info').height() + 50;

                $(".panel-info").removeClass("active");
                $(".mega-panel").removeClass("active").removeClass("inactive");
                $(".position-off").height("auto");
                $(this).next('.panel-info').addClass("active");
                $(this).parent().addClass("active");
                $('.mega-panel:not(.active)').addClass("inactive");
                $(this).parent().parent().height(wrapperHeight + containerInfoHeight);

                scrollToID($(this).next('.panel-info'));
            });

            $('.close-panel-btn').click(function(e) {
                $('.mega-panel').removeClass("active").removeClass("inactive");
                $(this).parent().parent().parent().height("auto");
                $('.panel-info').removeClass("active");
                scrollToID($(this).parent().parent());
            });
        }

        function clearSpeakerPanel() {
            if ($('.position-off')[0]) {
                var pos = $('.position-off')

                pos.removeAttr('style');

                if ($(window).width() >= 1200) {
                    var col_size = 'col-lg-';
                } else if ($(window).width() >= 992 && $(window).width() <= 1200) {
                    var col_size = 'col-md-';
                } else if ($(window).width() >= 768 && $(window).width() <= 992) {
                    var col_size = 'col-sm-';
                } else if ($(window).width() < 768) {
                    var col_size = 'col-xs-';
                }

                var cl = $('.position-off').attr('class');
                var cl_count = cl.slice(cl.indexOf(col_size) + 7);
                cl_count = Number(cl_count.slice(0, cl_count.indexOf(' ')));

                var colNum = 24 / cl_count

                var divCount = 0,
                    divMax = colNum;


                pos.each(function(e) {
                    divCount++;

                    if (divCount === 1) {
                        $(this).css('clear', 'both');
                    }
                    if (divCount >= divMax) {
                        divCount = 0;
                    }
                });
            }
        }

        function speakerPanelResize() {
            var container2 = $('.begin-content > .container').outerWidth(),
                contentWidth2 = $(".begin-content").outerWidth(),
                containerMargin2 = margin2 + 100,
                margin2 = (contentWidth2 - container2) / 2,
                mobile2 = 0,
                pos = $('.position-off');

            if ($(window).width() > 1440) {
                $(".panel-info").css("left", mobile2 + "px");
                $(".panel-info .container").css("margin-left", containerMargin2 + "px");
            }
            if ($(window).width() >= 768 && $(window).width() <= 1440) {
                $(".panel-info").css("left", mobile + "px");
                $(".panel-info .container").css({
                    "margin-left": margin2,
                    "margin-right": margin2,
                    "left": "0"
                });
            }
            if ($(window).width() < 768) {
                $(".panel-info").css("left", mobile + "px");
                $(".panel-info .container").css({
                    "margin-left": margin2,
                    "margin-right": margin2,
                    "left": "0"
                });
            }
        }

        speakerPanel();
        clearSpeakerPanel();
    }

    if (func._DA) {
        function compareHeightWidth() {
            // Loop and get Agenda Details Height
            $.each($('.agenda-item'), function(i, agendaItem) {
                // loop and store variable of class div.agenda-item (parent)
                var ai = $(agendaItem);
                // loop, find and store variable of div.agenda-header-mobile (child of div.agenda-item)
                var ai_hd_m = $(ai).find('.agenda-header-mobile');
                // loop and find div.agenda-header-desktop
                var ai_hd_d = $(ai).parent().find('.agenda-header-desktop');

                $.each(ai_hd_m.children(), function(j, ai_hd_m_child) {
                    var agDet = $(ai).find('.agenda-details-wrap').children()[j];
                    $(ai_hd_m_child).height($(agDet).outerHeight());
                });

                $.each(ai_hd_d.children(), function(k, ai_hd_d_child) {
                    var agDet2 = $(ai).find('.agenda-details-wrap').children()[k];
                    $(ai_hd_d_child).outerWidth($(agDet2).outerWidth());
                });
            });
        }
        compareHeightWidth();

        function agendaAccordion() {
            if ($('.agenda-item')[0]) {
                var accordionIcon = $('.accordion-btn');

                if ($(window).width() <= 992) {

                    accordionIcon.addClass('mobile-view');
                    compareHeightWidth();
                    $('.toggle-mobile').hide();
                    compareHeightWidth();

                    $('.accordion-btn').unbind().click(function(e) {
                        if (!$(this).parent().find('.toggle-mobile').is(':visible')) {
                            $(this).parent().find('.toggle-mobile').show();
                            compareHeightWidth();
                            $(this).parent().find('.toggle-mobile').hide();
                        } else {
                            compareHeightWidth();
                        }

                        $(this).parent().find('.toggle-mobile').toggle();
                        compareHeightWidth();

                        // Change arrow icon
                        $(this).toggleClass('active');

                    });
                }
                if ($(window).width() >= 992) {
                    $('.toggle-mobile').show();
                    accordionIcon.removeClass('mobile-view');
                }
            }
        }
        agendaAccordion();

        function eventSlider() {
            if ($('.event-slider')[0]) {
                $('.event-slider').bxSlider({
                    // auto: true,
                    preventDefaultSwipeY: false,
                    speed: 800,
                    controls: true,
                    pager: true,
                    onSlideAfter: function(slideElement, oldIndex, newIndex) {
                        changeAgendaTable(slideElement);
                    }
                });
            }
        }

        function changeAgendaTable(slideElement) {
            var agendaIndexToLoad = $(slideElement).attr("data-index");
            $('.agenda-block').addClass('hide');
            $('#agenda-block-' + agendaIndexToLoad).removeClass('hide');
            compareHeightWidth();
            agendaAccordion();
        }

        eventSlider();
    }

    if (func._TVS) {
        var channelListing = $('.channel-listing'),
            container = $('.begin-content > .container').outerWidth(),
            contentWidth = $(".begin-content").outerWidth(),
            margin = (contentWidth - container) / 2,
            containerMargin = margin + 100,
            mobile = 0,
            divMax = 0;

        if ($(window).width() > 1440) {
            $(".panel-info").css("left", mobile + "px");
            // $(".panel-info .container").css("margin-left", containerMargin + "px");
        }
        if ($(window).width() >= 768 && $(window).width() <= 1440) {
            // $(".panel-info").css("left", margin + "px");
            // $(".panel-info .container").css("margin", mobile + "px");
            $(".panel-info").css("left", mobile + "px");
            // $(".panel-info .container").css({
            //     "margin-left": margin,
            //     "margin-right": margin,
            //     "left": "0"
            // });
        }
        if ($(window).width() < 768) {
            $(".panel-info").css("left", mobile + "px");
            // $(".panel-info .container").css({
            //     "margin": "0",
            //     "left": "0"
            // });
        }

        function channelPanel() {
            var chDiv = channelListing.find('.cards');
            var closeBtn = channelListing.find('.close-panel-btn');

            chDiv.click(function(e) {

                e.preventDefault();

                // if i clicked on a mega-panel
                if ($(e.target).hasClass('mega-panel') ||
                    // or i clicked on a smthing whose parent is a mega-panel but it's parent cant be a btn
                    ($(e.target).parent().hasClass('mega-panel') && $(e.target).parents('.btn').length == 0 && !$(e.target).hasClass('btn')) ||
                    // or i clicked on a div whose parent-parent-parent could be a mega-panel and whose parent is not a panel-info but it's parents cant be a btn or it cant be a btn
                    ($(e.target).parents('.mega-panel').length != 0 && $(e.target).parents('.panel-info').length == 0 && $(e.target).parents('.btn').length == 0 && !$(e.target).hasClass('btn'))) {
                    var wrapperHeight = 476;
                    var containerInfoHeight = $(this).next('.panel-info').height() + 50;

                    channelListing.find(".panel-info").removeClass("active");
                    channelListing.find(".mega-panel").removeClass("active inactive")

                    $(".position-off").height("auto");

                    $(this).find('.panel-info').addClass("active");

                    $(this).addClass("active");
                    $('.mega-panel:not(.active)').addClass("inactive");
                    var po_ht = $(this).parents('.position-off').height();
                    $(this).parents('.position-off').height($(this).find('.panel-info').outerHeight() + po_ht);

                    scrollToID($(this).find('.panel-info'));
                }
                // but if i did click on a btn or its parent-parent-parent could be a button
                else if (($(e.target).hasClass('btn') || $(e.target).parents('.btn').length != 0)) {
                    //$(this).find('.add-remove').toggleClass('add');
                }
            });

            closeBtn.click(function(e) {
                $('.position-off').css('height', 'auto');
                $(this).parents('.active').removeClass('active');
                $('.mega-panel').removeClass('inactive');
            });
        }

        function clearChannelPanel() {

            $.each(channelListing, function(ch_row_i, channelListingRow) {
                var channelRow = $(channelListingRow);
                var pos = channelRow.find('.position-off');

                pos.removeAttr('style');

                if ($(window).width() >= 1200) {
                    var col_size = 'col-lg-';
                } else if ($(window).width() >= 992 && $(window).width() < 1200) {
                    var col_size = 'col-md-';
                } else if ($(window).width() >= 768 && $(window).width() < 992) {
                    var col_size = 'col-sm-';
                } else if ($(window).width() < 768) {
                    var col_size = 'col-xs-';
                }

                var cl = pos.attr('class');
                var cl_count = cl.slice(cl.indexOf(col_size) + 7);
                cl_count = Number(cl_count.slice(0, cl_count.indexOf(' ')));

                var colNum = 24 / cl_count

                var divCount = 0,
                    divMax = colNum;

                pos.each(function(e) {
                    divCount++;

                    if (divCount === 1) {
                        $(this).css('clear', 'both');
                    }
                    if (divCount >= divMax) {
                        divCount = 0;
                    }
                });
            });
        }

        function channelPanelResize() {
            // console.log("speakerPanelResize");
            var container2 = $('.begin-content > .container').outerWidth(),
                contentWidth2 = $(".begin-content").outerWidth(),
                containerMargin2 = margin2 + 100,
                margin2 = (contentWidth2 - container2) / 2,
                mobile2 = 0,
                pos = $('.position-off');

            // if ($(window).width() > 1440) {
            $(".panel-info").css("left", mobile2 + "px");
            $(".panel-info .container").css("margin-left", containerMargin2 + "px");
            // }
            // if ($(window).width() >= 768 && $(window).width() <= 1440) {
            // $(".panel-info").css("left", mobile + "px");
            // $(".panel-info .container").css({
            //     "margin-left": margin2,
            //     "margin-right": margin2,
            //     "left": "0"
            // });
            // }
            // if ($(window).width() < 768) {
            // $(".panel-info .container").css({
            //     "margin": "0",
            //     "left": "0"
            // });
            // }
        }
        channelPanel();
        clearChannelPanel();
    }

    if (func._SPR) {
        var rowsWithAutoSplit = $('.row.split-row');

        function rasEqHt() {
            // console.log('rasEqHt()');
            if (!rowsWithAutoSplit) {
                return false;
            } else {
                $.each(rowsWithAutoSplit, function(i, ras) {
                    // console.log('added js-autosplit');

                    $(ras).wrapInner('<div class="col-lg-24 js-autosplit"></div>');

                    // based on the first col in the jsAutosplit list it will then dictate the spilt
                    initRasEqHt(ras);
                });
            }
        }

        function initRasEqHt(_ras) {

            var isFooter = $(_ras).parents('footer').length != 0 ? true : false;
            var jsAutosplit = $(_ras).find('.js-autosplit');
            var firstCol = func._localhost ?
                $(jsAutosplit.children()[0]) :
                $($(jsAutosplit.find('.section')[0]).children())

            firstCol = func._staging || isFooter ?
                $(jsAutosplit.children()[0]) :
                firstCol
            var firstColClass = firstCol.attr('class');
            var col_sizes = ['col-lg-', 'col-md-', 'col-sm-', 'col-xs-'];
            var col_size_id;
            var col_per_row;
            var col_count_row = 0;
            var col_per_row_array = [];

            if ($(window).width() >= 1200) {
                col_size_id = 0; // test for col-lg- first
            } else if ($(window).width() >= 992 && $(window).width() < 1200) {
                col_size_id = 1; // test for col-md- first
            } else if ($(window).width() >= 768 && $(window).width() < 992) {
                col_size_id = 2; // test for col-sm- first
            } else if ($(window).width() < 768) {
                col_size_id = 3; // test for col-xs- first
            }


            for (var i = col_size_id; i < col_sizes.length; i++) {

                var _id = firstColClass.indexOf(col_sizes[i]);
                var _idi;

                if (_id != -1) {
                    _idi = firstColClass.slice(_id + 7);
                    var last = _idi.indexOf(' ') == -1 ? _idi.length : _idi.indexOf(' ');

                    _idi = Number(_idi.slice(0, last));
                    col_per_row = 24 / _idi;

                    break;
                }
            };

            var jsAutosplitRow;

            var jsAutosplitCol = func._localhost || isFooter ?
                jsAutosplit.children() :
                jsAutosplit.find('.section');

            $.each(jsAutosplitCol, function(j, col) {
                col_count_row++;

                if (col_count_row == 1) {
                    jsAutosplit.append('<div class="row js-autosplit-row">');
                    jsAutosplitRow = jsAutosplit.find('.js-autosplit-row').last();
                    // console.log(jsAutosplitRow);
                }

                jsAutosplitRow.append($(col));

                if (col_count_row == col_per_row) {
                    col_count_row = 0;
                }
            });

            processRasEqHt(_ras);
        }

        function processRasEqHt(_ras) {
            // console.log('processRasEqHt()')
            return false;

            $.each($(_ras).find('.js-autosplit-row'), function(k, as_row) {

                var asEqHtContCol = $(as_row).find('.eq-ht-cont');
                var ht = 0;
                var total_bottom_link = 0;
                var total_social_media = 0;
                var total_stn_btn = 0;
                var total_tstm_btn = 0;
                var total_promo_btn = 0;
                var total_green_btn = 0;

                $.each(asEqHtContCol, function(j, asEqH) {

                    $(asEqH).removeAttr('style');

                    if ($(asEqH).height() > ht) {
                        ht = $(eqH).height();
                    }

                    if ($(asEqH).find('.bottom-link').length > total_bottom_link) {
                        total_bottom_link = $(asEqH).find('.bottom-link').length;
                    }

                    if ($(asEqH).find('.socialMediaWrapper').length > total_social_media) {
                        total_social_media = $(asEqH).find('.socialMediaWrapper').length;
                    }

                    if ($(asEqH).find('.btn.btn-pri.btn-stn.btn-sky-blue').length > total_stn_btn) {
                        total_stn_btn = $(asEqH).find('.btn.btn-pri.btn-stn.btn-sky-blue').length;
                    }

                    if ($(asEqH).find('.tstm-link').length > total_tstm_btn) {
                        total_tstm_btn = $(asEqH).find('.tstm-link').length;
                    }

                    if ($(asEqH).find('.promo-bottom-link').length > total_bottom_link) {
                        total_promo_btn = $(asEqH).find('.promo-bottom-link').length;
                    }

                    if ($(asEqH).find('.btn-green').length > total_green_btn) {
                        total_green_btn = $(asEqH).find('.btn-green').length;
                    }
                });

                if (total_bottom_link == 1 && total_social_media >= 1 && total_stn_btn == 1) {
                    ht += 100;
                    // console.log('a');
                } else if (total_bottom_link == 0 && total_social_media >= 1 && total_stn_btn == 1) {
                    ht += 80;
                    // console.log('b');
                } else if (total_bottom_link == 1 && total_social_media == 0 && total_stn_btn == 1) {
                    ht += 60;
                    // console.log('c');
                } else if (total_bottom_link == 1 && total_social_media >= 1 && total_stn_btn == 0) {
                    ht += 100;
                    // console.log('d');
                } else if (total_bottom_link == 0 && total_social_media == 0 && total_stn_btn == 1) {
                    ht += 75;
                    // console.log('e');
                } else if (total_bottom_link == 1 && total_social_media == 0 && total_stn_btn == 0) {
                    ht += 90;
                    // console.log('f');
                } else if (total_tstm_btn == 1) {
                    ht += 50;
                    // console.log('g');
                } else if (total_promo_btn == 1 && total_social_media >= 1) {
                    ht += 70;
                    // console.log('h');
                } else if (total_promo_btn == 0 && total_social_media >= 1 && total_bottom_link == 0) {
                    ht += 70;
                    // console.log('i');
                } else if (total_promo_btn == 1) {
                    ht += 120;
                    // console.log('j');
                } else if (total_green_btn == 1) {
                    ht += 50;
                    // console.log('k');
                } else {
                    // console.log('l', row, total_social_media, total_bottom_link);
                }

                if ($(window).width() > 768) {
                    asEqHtContCol.height(ht);
                } else {
                    asEqHtContCol.removeAttr('style');
                }
            });
        }

        function resizeRasEqHt() {
            if (!rowsWithAutoSplit) {
                return false;
            } else {

                var asS = rowsWithAutoSplit.find('.js-autosplit-row');

                $.each(asS, function(j, _asS) {
                    $($(_asS).children()[0]).unwrap();
                });

                $.each(rowsWithAutoSplit, function(i, ras) {
                    initRasEqHt(ras);
                })
            }
        }
    }

    if (func._MBX) {
        var blogBx = $('.mobile-only-bxslider ul.row'),
            blogBxA = []

        $.each(blogBx, function(i, blogBxs) {
            var bxs = $(blogBxs).bxSlider({
                preventDefaultSwipeY: false,
                controls: false
            })

            blogBxA.push(bxs);
        });

        function mobileBxSlider() {
            if ($(window).width() < 767) {
                $.each(blogBxA, function(i, blogBxs) {
                    blogBxs.reloadSlider();
                });
            } else {
                $.each(blogBxA, function(i, blogBxs) {
                    blogBxs.destroySlider();
                });
            }
        }

        mobileBxSlider();
    }

    if (func._NUM) {
        setTimeout(function() {
            $("input.numOnly").keypress(function(e) {
                //if the letter is not digit then display error and don't type anything
                if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                    //display error message
                    return false;
                }
            });

        }, 200);
    }

    if (func._CP) {
        //var calResult = $('.cal-results').outerHeight();
        //$('.change-phone').css('height', calResult);

        $('.btn-changePhone').click(function(e) {
            $('.change-phone-wrapper').fadeIn('medium');
            $('.change-phone-slider').bxSlider({
                preventDefaultSwipeY: false,
                slideWidth: 135,
                minSlides: 2,
                maxSlides: 6,
                slideMargin: 60,
                controls: false
            });
            e.preventDefault();
        });

        $('.btn-close').click(function(e) {
            $('.change-phone-wrapper').fadeOut('medium');
            e.preventDefault();
        });
    }

    if (func._ACC) {
         //console.log("Accordion")
        function widgetAccordionClick() {
            $('.accordion__content--wrap > h2').click(function(e) {
                e.preventDefault();
                $(this).toggleClass('active');
                $(this).next().toggleClass('active');
            });
        }
        widgetAccordionClick();
    }

    if (func._SUP) {
        // console.log("support on")
        function widgetSupportClick() {
            $('.support-list > li span').click(function(e) {
                $(this).toggleClass('active');
                $(this).next().toggleClass('active');
            });
        }
        widgetSupportClick();
    }

    if (func._GAL) {
        // console.log("Gallery On")
         $('.gallery > .gallery-thumbnail-image').click(function(e) {
             // var getImageHeight = $(this).hasClass('.gallery-main-image').height();
             var wrapperHeight = $('.gallery').outerHeight();
             var containerInfoHeight = $(this).next().height()+50;
             var nextBtn = $('.next-btn');
             var prevBtn = $('.prev-btn');
             var zero = 0;
             var currId = $(e.currentTarget).parents('.gallery-item').index();
             var totalId = $(e.currentTarget).parents('.gallery-row').find('.gallery-item').length-1;

            // console.log(currId)
            // console.log(totalId)
            // console.log(containerInfoHeight)
            // console.log(wrapperHeight)

            // setTimeout(function() {
            //    console.log(getImageHeight)
            // }, 500);

            if (currId == zero) {
               prevBtn.addClass('disable')
            }
            else {
               prevBtn.removeClass('disable')
            }

            if (currId == totalId) {
               nextBtn.addClass('disable')
            }
            else {
               nextBtn.removeClass('disable')
            }

             $(".panel-info").removeClass("active");
             $(".mega-panel").removeClass("active").removeClass("inactive");
             $(".position-off").height("auto");
             $(this).next('.panel-info').addClass("active");
             $(this).parent().addClass("active");
             $('.mega-panel:not(.active)').addClass("inactive");
             $(this).parent().parent().height(containerInfoHeight+wrapperHeight);
             scrollToID($(this).next('.panel-info'));
         });

         $('.close-panel-btn').click(function(e) {
             $('.mega-panel').removeClass("active").removeClass("inactive");
             $(this).parent().parent().parent().parent().parent().height("auto");
             $('.panel-info').removeClass("active");
             scrollToID($(this).parent().parent());
         });

         function calGalleryHeight() {
            $('.mega-panel').removeClass("active").removeClass("inactive");
            $('.position-off.gallery-item').height("auto");
            $('.panel-info').removeClass("active");
            // var activeGallery = $('.gallery.active');
            // var panelHeight1200 = $('.panel-info.active').outerHeight() + 156;
            // var panelHeight1000 = $('.panel-info.active').outerHeight() + 133;
            // var panelHeight992 = $('.panel-info.active').outerHeight() + 190;
            // var panelHeight768 = $('.panel-info.active').outerHeight() + 300;
            // var panelHeightMob = $('.panel-info.active').outerHeight() + 120;

            // if ($(window).width() >= 1200) {
            //    activeGallery.parent().height(panelHeight1200);
            //    console.log("panelHeight1200")
            // } else if ($(window).width() >= 1000 && $(window).width() <= 1200) {
            //    activeGallery.parent().height(panelHeight1000);
            //    console.log("panelHeight1000")
            // } else if ($(window).width() >= 992 && $(window).width() <= 1000) {
            //    activeGallery.parent().height(panelHeight992);
            //    console.log("panelHeight992")
            // } else if ($(window).width() >= 768 && $(window).width() <= 992) {
            //    activeGallery.parent().height(panelHeight768);
            //    console.log("panelHeight992")
            // } else if ($(window).width() < 768) {
            //    activeGallery.parent().height(panelHeightMob);
            //    console.log("panelHeightMob")
            // }
         }

         function clearGalleryPanel() {
            if ($('.position-off')[0]) {
                var pos = $('.position-off')

                pos.removeAttr('style');

                if ($(window).width() >= 1200) {
                    var col_size = 'col-lg-';
                } else if ($(window).width() >= 992 && $(window).width() <= 1200) {
                    var col_size = 'col-md-';
                } else if ($(window).width() >= 768 && $(window).width() <= 992) {
                    var col_size = 'col-sm-';
                } else if ($(window).width() < 768) {
                    var col_size = 'col-xs-';
                }

                var cl = $('.position-off').attr('class');
                var cl_count = cl.slice(cl.indexOf(col_size) + 7);
                cl_count = Number(cl_count.slice(0, cl_count.indexOf(' ')));

                var colNum = 24 / cl_count

                var divCount = 0,
                    divMax = colNum;


                pos.each(function(e) {
                    divCount++;

                    if (divCount === 1) {
                        $(this).css('clear', 'both');
                    }
                    if (divCount >= divMax) {
                        divCount = 0;
                    }
                });
            }
         }
         clearGalleryPanel();


         function directionControlGallery() {

            $('.gallery-item .control .next-btn').click(function (e) {
               var zero = 0;
               // var nextBtn = $('.next-btn');
               var currId = $(e.currentTarget).parents('.gallery-item').index();
               var totalId = $(e.currentTarget).parents('.gallery-row').find('.gallery-item').length-1;
               var nextGal = $('.gallery-item').eq(currId + 1);
               var nextImg = $(nextGal).find('.gallery>img');

               // console.log('click', this, currId, totalId);
               // console.log(currId)

               nextImg.trigger('click');
            });

            $('.gallery-item .control .prev-btn').click(function (e) {
               var zero = 0;
               // var prevBtn = $('.prev-btn');
               var currId = $(e.currentTarget).parents('.gallery-item').index();
               var totalId = $(e.currentTarget).parents('.gallery-row').find('.gallery-item').length-1;
               var prevGal = $('.gallery-item').eq(currId - 1);
               var prevImg = $(prevGal).find('.gallery>img');

               // console.log('click', this, currId, totalId);
               // console.log(currId)

               prevImg.trigger('click');
            });
         }
         directionControlGallery();
    }

    if (func._OLI) {
        $('.bullet-wrapper .bullet-container ol').addClass('ordered-list');
    }

    if (func._IMP) {
        $('.important-msg .close-btn').click(function (e) {
         // console.log('close')
            $(this).parent().parent().parent().parent().addClass('disable');
        });
    }

    if (func._FUL) {
      var src = $('.global-landing.full-width .bs-src-global').attr('data-src');
      $(".global-landing").backstretch(src);
    }

    if (func._PLI) {
      var personalItems = {
        itemSelector: '.grid-splash-item',
        masonry: {
          columnWidth: '.grid-splash-item'
        },
        transitionDuration: 0
      }

      $('.grid').isotope(personalItems);
    }

    if (func._DABA) {
        var dataBind = $('[data-binding]');
        var procBind = $('#proc-binding');

        $.each(dataBind, function(i, db) {
            getValueDB(db);
            $(db).click(function () { getValueDB(this);
             var ff = $('.fake-form');

            $.each(ff, function(i, _ff) {
                var v = $(_ff).find('input').val();
                // console.log(v);
                $(_ff).find('.fake-form-control').html(v);
                $(_ff).find('.valTemp').attr('value', v);
            });
            });
            // if ($(db).attr('proc-binding') != "") {
            //     var attrProc    = $(db).attr('proc-binding')
            //     var procBinding = $('#' + attrProc);

            //     procBinding.click(function (e) {
            //         getValueDB(db);
            //         e.preventDefault();
            //     });
            // }
        });

        procBind.click(function (e) {
            var ff = $('.fake-form');

            $.each(ff, function(i, _ff) {
                var v = $(_ff).find('input').val();
                // console.log(v);
                $(_ff).find('.fake-form-control').html(v);
                $(_ff).find('.valTemp').attr('value', v);
            });

            e.preventDefault();
        });

        function getValueDB (_db) {
            var v           = $(_db).attr('data-binding');
            var sel         = $(_db).find('.active');
            if(sel.parents('.storageWrapper').length>0){
                  var storageRow = sel.parents('.storageWrapper').find('.row');
                $.each(storageRow, function(i, _ff) {
                    if(!$(_ff).hasClass("hidden")){
                    sel=$(_ff).find('.active')
                    }

            });

            }
            var passData    = sel.find('.pass-data');

            var label       = passData.find('.label').text();
            var plan        = passData.find('.plan').text();
            var _input      = $('#' + v);


            // _input.find('.fake-label').html(label)
            // _input.find('.fake-form-control').html(plan)
            _input.find('input').val(plan);
        }
    }

    if (func._PLA) {

        // console.log('hello');
      var $anchors = $('.anchor-link');

        // console.log($('.anchor-link'), 'here');

      if ($('.main-content table').length > 0) {
          var $items = $('.cell-green')
          $anchors.click(function(e) {
             var selectedIndex = $anchors.index(this);
             // console.log(selectedIndex, this);
             // $anchors.removeClass('active').eq(selectedIndex).addClass('active');
             $items.removeClass('active').eq(selectedIndex).addClass('active');

             $('html, body').animate({
                scrollTop: $( $.attr(this, 'href') ).offset().top
             }, 500);
             return false;
          });
      }
    }

    if (func._LOG) {
      // console.log("LOG")
      $('.user-name').hover(function() {
         // console.log("drop")
        $('.login-dropdown-menu').toggleClass('active');
      });
      $('.login-dropdown-menu').hover(function() {
         // console.log("drop")
        $(this).toggleClass('active');
      });
    }

    if (func._VP) {
        var container = $('.begin-content > .container').outerWidth(),
            contentWidth = $(".begin-content").outerWidth(),
            margin = (contentWidth - container) / 2,
            containerMargin = margin + 100,
            mobile = 0,
            divMax = 0;
            videoPlayer = $('.video-player'),
            videoRelProd = videoPlayer.find('.sh-video-product ul.row'),
            vidBx = $('.video-bxslider ul.row'),
            vidBxA = [];

        function videoPanel() {
            var posHeight = $('.mega-video-panel').outerHeight();

            $('.video-link').click(function(e) {
                // var wrapperHeight = 1830;
                // var containerInfoHeight = $(this).next('.video-panel').height() + 50;
                // console.log(posHeight)

                if ($(window).width() >= 1200) {
                    var wrapperHeight = 1880;
                    $(this).parent().parent().siblings('.video-panel').css('top', posHeight + 260);
                    // console.log("height")
                } else if ($(window).width() >= 992 && $(window).width() <= 1200) {
                    // var wrapperHeight = 1810;
                    // $(this).parent().parent().siblings('.video-panel').css('top', posHeight + 260);
                    $(this).parent().parent().siblings('.video-panel').css('top', '20px');
                } else if ($(window).width() >= 768 && $(window).width() <= 992) {
                    // var wrapperHeight = 1690;
                    $(this).parent().parent().siblings('.video-panel').css('top', '20px');
                } else if ($(window).width() >= 500 && $(window).width() <= 768) {
                    // var wrapperHeight = 1900;
                    $(this).parent().parent().siblings('.video-panel').css('top', '20px');
                } else if ($(window).width() < 500) {
                    // var wrapperHeight = 1915;
                    $(this).parent().parent().siblings('.video-panel').css('top', '20px');
                    $(this).parent().parent().parent().css({
                        'height' : 'auto',
                        // 'margin-bottom' : '45px'
                    });
                }

                e.preventDefault();
                $(".video-panel").removeClass("active");
                $(".mega-video-panel").removeClass("active");
                $(".video-position-off").height("auto");
                $(this).parent().parent().siblings('.video-panel').addClass("active");
                $(this).parent().parent().addClass("active");
                $('.mega-video-panel:not(.active)').addClass("inactive");

                // $(this).parent().parent().parent().height(wrapperHeight + containerInfoHeight);
                $(this).parent().parent().parent().height(wrapperHeight);

                setTimeout(function(){
                  videoBxSlider();
                  vidEqHt();
                }, 500);

                scrollToID($(this).parent().parent().siblings('.video-panel.active'));
            });

            $('.close').click(function(e) {
                $(this).parent().parent().parent().parent().parent().parent().height("auto");
                $('.video-panel').removeClass("active");
                $('.mega-video-panel').removeClass("active");
                $(".mega-video-panel").removeClass("inactive");
                scrollToID($(this).parent().parent().parent().parent().parent().parent());
            });
        }

        function clearVideoPanel() {
            if ($('.video-position-off')[0]) {
                var pos = $('.video-position-off')

                pos.removeAttr('style');

                if ($(window).width() >= 1200) {
                    var col_size = 'col-lg-';
                } else if ($(window).width() >= 992 && $(window).width() <= 1200) {
                    var col_size = 'col-md-';
                } else if ($(window).width() >= 768 && $(window).width() <= 992) {
                    var col_size = 'col-sm-';
                } else if ($(window).width() < 768) {
                    var col_size = 'col-xs-';
                }

                var cl = $('.video-position-off').attr('class');
                var cl_count = cl.slice(cl.indexOf(col_size) + 7);
                cl_count = Number(cl_count.slice(0, cl_count.indexOf(' ')));

                var colNum = 24 / cl_count

                var divCount = 0,
                    divMax = colNum;


                pos.each(function(e) {
                    divCount++;

                    if (divCount === 1) {
                        $(this).css('clear', 'both');
                    }
                    if (divCount >= divMax) {
                        divCount = 0;
                    }
                });
            }
        }

        function videoPanelResize() {
            $(".video-panel").removeClass("active");
            $(".video-position-off").height("auto");
            $('.mega-video-panel').removeClass("active");
            $(".mega-video-panel").removeClass("inactive");
        }

        $.each(vidBx, function(i, vidBxs) {
            var vxs = $(vidBxs).bxSlider({
                preventDefaultSwipeY: false,
                controls: false,
                minSlides: 3,
                maxSlides: 4,
                slideWidth: 170,
                slideMargin: 12,
                infiniteLoop: false
            })

            vidBxA.push(vxs);
        });

        function vidEqHt() {
            var h = 0;
            if (videoRelProd == undefined)
            {
                return false;
            }
            videoRelProd.find('.eq-ht-cont').removeAttr('style');
            $.each(videoRelProd.find('.eq-ht-cont'), function(i, col) {
                var col = $(col);
               // console.log(col.height())
                if (col.height() > h) {
                    h = col.height();
                }
            });
            videoRelProd.find('.eq-ht-cont').height(h);
            // func._forceResize = false;
            // console.log("height", h)
        }

        function videoBxSlider() {
            var colWidth = 0,
                colMargin = 12;
            // console.log("videoBxSlider", videoRelProd)
            if (videoRelProd == undefined)
            {
                return false;
            }
            var bxPager = true;

            if ($(window).width() >= 1200) {
                colWidth = (videoPlayer.find('.video-bxslider:visible').width() / 4) - colMargin;
                bxPager = videoPlayer.find('.video-bxslider:visible li').not('.bx-clone').length > 4 ? true : false;
            } else if ($(window).width() >= 992) {
                colWidth = (videoPlayer.find('.video-bxslider:visible').width() / 3) // - colMargin;
                bxPager = videoPlayer.find('.video-bxslider:visible li').not('.bx-clone').length > 3 ? true : false;
            } else if ($(window).width() >= 480) {
                colWidth = (videoPlayer.find('.video-bxslider:visible').width() / 2) // - colMargin;
                bxPager = videoPlayer.find('.video-bxslider:visible li').not('.bx-clone').length > 2 ? true : false;
            } else if ($(window).width() < 480) {
                colWidth = (videoPlayer.find('.video-bxslider:visible').width()) // - colMargin;
                bxPager = videoPlayer.find('.video-bxslider:visible li').not('.bx-clone').length > 1 ? true : false;
            }

            $.each(vidBxA, function(i, vidBxs) {
                vidBxs.reloadSlider({
                    preventDefaultSwipeY: false,
                    controls: false,
                    infiniteLoop: false,
                    pager: bxPager,
                    minSlides: 1,
                    maxSlides: 4,
                    slideWidth: colWidth,
                    slideMargin: colMargin
                });
            });
        }

        videoPanel();
        clearVideoPanel();
    }

    if (func._TBS) {
       var img = $('.cards-img-bg').find('.bs-src');
       for (i=0; i<img.length; i++)
       {
           if (img.attr('src') != undefined && img.attr('src') != '') {
               $(img[i]).parent().backstretch($(img[i]).attr('src'))
               img.hide();
           }
       }
    }

    if (func._VSLIDE) {
      // console.log('vslide')

      var sliderHeight = $('.bx-wrapper:visible').outerHeight(),
          bs = $('.main-graphic.backstretch'),
          vr = $('.slider-video-block'),
          iFrame = $('#ytplayer'),
          desktopLoad = $('.desktop-masthead .watch-video-button').first().attr('data-url'),
          mobileLoad = $('.mobile-masthead .watch-video-button').first().attr('data-url'),
          vheight = 529,
          cheight = $('.main-graphic.backstretch').outerHeight(),
          vzero = 0;

        var player;
        window.onYouTubePlayerAPIReady = function() {
          // console.log("youtube")
          player = new YT.Player('ytplayer', {
          height: '',
          width: '',
          playerVars: {
            'autoplay': 1,
            'controls': 1 ,
            'modestbranding': 1,
            'showinfo': 0,
            'rel': 0,
            'fs': 1
         },
          videoId: '',
          events: {
              'onReady': onPlayerReady,
              'onStateChange': ShowMe
             }
          });
        }

       var tag = document.createElement('script');
       tag.src = "http://www.youtube.com/player_api";
       var firstScriptTag = document.getElementsByTagName('script')[0];
       firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

       function onPlayerReady(event) {
         $('.watch-video-button').click(function(e) {
            vda = $(this).attr('data-url').replace('https://www.youtube.com/embed/','');
            vr.animate({
               height: vheight
            },500);
            bs.animate({
               height: vzero,
               opacity: 0
            },500);

            player.loadVideoById(vda);
         });
       }

       function ShowMe() {
          // console.log("showMe")
          var sStatus;
          sStatus = player.getPlayerState();

           if (sStatus == 0) {
               // console.log("video ended!")
               vr.animate({
                  height: vzero
               },500);
               bs.animate({
                  height: cheight,
                  opacity: 1
               },500);
               player.stopVideo();
            //change above function to react to when the player stops.
           }
        }

      $('.slider-video-bar .close-btn').click(function(e) {
         var sStatus;
         sStatus = player.getPlayerState();
         vr.animate({
            height: vzero
         },500);
         bs.animate({
            height: cheight,
            opacity: 1
         },500);
         // $('#ytplayer').removeAttr('src');
         // $("#ytplayer")[0].pause();
         player.stopVideo();
         // console.log("stop")
      });

      function videoSiderResize() {
         // vr.slideUp(500);
         // setTimeout(function() {
         //    $('.diagonal').removeClass('hide');
         //    $('.bx-controls').removeClass('hide');
         // }, 400);
      }
    }

    if (func._SCROLL) {
      var prevScroll = 0,
          curDir = 'down',
          prevDir = 'up',
          head = 300;

         $(window).scroll(function(){
            if ($(this).scrollTop() >= prevScroll){
                curDir = 'down';
                if(curDir != prevDir){
                $('#sticky-mobile').stop();
                $('#sticky-mobile').animate({ top: '-126px' }, 300);
                prevDir = curDir;
                // console.log("hide")
            }
            } else {
                if($(this).scrollTop() <= 450 ){
                   $('#sticky-mobile').stop();
                   $('#sticky-mobile').animate({ top: '-126px' }, 50);
                   // console.log("hide top")
                }
                else {
                  curDir = 'up';
                  if(curDir != prevDir){
                      $('#sticky-mobile').stop();
                      $('#sticky-mobile').animate({ top: '0px' }, 300);
                      prevDir = curDir;
                      // console.log("show")
                   }
                }
            }
            prevScroll = $(this).scrollTop();
         });
    }

    if (func._POPLINK) {
      var option = $('.mobile-tab-nav .form-control option');
      var values = $.map(option ,function(options) {
          return options.value;
      });
      // console.log(values)
      $(".mobile-tab-nav .dropdown-menu.inner a").each(function(index) {
          $(this).attr("href", values[index]);
          // console.log("yeah baby")
      });
      // console.log(selected);

      $('.mobile-tab-nav .dropdown-menu.inner a').click(function (e) {
         e.preventDefault();
         var url = $(this).attr("href");
         window.location = url;
         return false;
         // console.log(url)
      });
    }

    if (func._GREYBG) {
      function calGreybgHeight() {
         if ($(window).width() < 768) {
               setTimeout(function() {
               var mobileHt = $('.mobilepromoindiv_v1 .container .row > div:first-child .mobile-phone').outerHeight();
               var contentHt = $('.mp-text .mobileprice').outerHeight() + 120;
               $('.tstm-grey.mp').height(mobileHt+contentHt);
               // console.log(mobileHt)
               // console.log(contentHt)
            }, 500);
         }
         else {
            $('.tstm-grey.mp').removeAttr('style');
         }
      }
      calGreybgHeight();
    }

    if (func._TTL) {
        $( ".cards.material.noShadow a.btn" ).hover(
            function() {
                $(this).parents('.noShadow').addClass('boxShadow');
            }, function() {
                $(this).parents('.noShadow').removeClass('boxShadow');
            }
        );
    }

    // Global Resize Function

    var globalResizeId;

    $(window).resize(function(event) {
        if (func._windowWidth != $(window).width() || func._forceResize) {
            clearTimeout(globalResizeId);
            globalResizeId = setTimeout(function() {
                if (func._SK) {
                    listenToResizeSK()
                }
                if (func._BS) {
                    listenToResizeBS()
                }
                if (func._BLX) {
                    listenToResizeBLX()
                }
                if (func._FILC) {
                    listenToResizeFILC()
                }
                if (func._JVA) {
                    listenToResizeJVA()
                }
                if (func._BHT) {
                    blogHt()
                }
                if (func._TTS) {
                    listenToResizeTTS()
                }
                if (func._SPP) {
                    speakerPanelResize();
                    clearSpeakerPanel()
                }
                if (func._DA) {
                    compareHeightWidth();
                    agendaAccordion()
                }
                if (func._TVS) {
                    channelPanelResize();
                    clearChannelPanel()
                }
                if (func._EHT) {
                    colEqHt();
                }
                if (func._MBX) {
                    mobileBxSlider()
                }
                if (func._SPR) {
                    resizeRasEqHt();
                }
                if (func._CTBX) {
                    initChTxtBxSlider();
                }
                if (func._VP) {
                    videoPanelResize();
                    vidEqHt();
                    videoBxSlider();
                    clearVideoPanel();
                }
                if (func._VSLIDE) {
                    videoSiderResize();
                }
                if (func._GREYBG) {
                    calGreybgHeight();
                }
                if (func._GAL) {
                    calGalleryHeight();
                }
            }, 500);

            func._windowWidth = $(window).width();
        }

        if (func._forceResize) {
            func._forceResize = false;
        }
    });

});

function isThereBtnBreadcrumb() {
    if ($('.btn-breadcrumb').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereStBreadcrumb() {
    if ($('.st-breadcrumb').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereSideKickNav() {
    if ($('#side-kick-nav').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereBackstretch() {
    if ($('.backstretch').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereDotDotDot() {
    if ($('.dotdotdot').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereBxSlider() {
    if ($('.bxslider').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereBlendExtend() {
    if ($('.blend-extend').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereFilter() {
    if ($('.filters').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereSlider() {
    if ($('.slider').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereSelectPicker() {
    if ($('.select-picker').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereInputMask() {
    if ($('input[data-inputmask]').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereCalender() {
    if ($('.load-calendar').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereAutoCompleteTextfield() {
    if ($('.textfield-autocomplete').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereJVerticalAlign() {
    if ($('.jVerticalAlign').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereEqualHeightColumn() {
    if ($('.eq-ht-cont').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereShowMoreShowLessTags() {
    if ($('.dotTags').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereFilterComp() {
    if ($('.filter-component').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereSocialMediaIcon() {
    if ($('ul.socialMedia').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereFooterAccordion() {
    if ($('.site-map-footer #accordion').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereBlogHeight() {
    if ($('.featured-product-content').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereGoogleMap() {
    if ($('#map_canvas').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereVerticalBorder() {
    if ($('.vr').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereTestimonialSlider() {
    if ($('.testimonial-slider').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereSpeakerPanel() {
    if ($('.speaker-info').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereDetailsAgenda() {
    if ($('.agenda-block').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereTVSelection() {
    if ($('.channel-listing').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereSplitRow() {
    // auto split row.
    // each row can only have 24 col
    if ($('.row.split-row').length != 0) {
        return true;
    } else {
        return false;
    }
}

function scrollToID(ID) {
    $('html, body').animate({
        scrollTop: $(ID).offset().top - 130
    }, 500);
}

function isThereNumberOnly() {
    if ($('.numOnly').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereMobileOnlyBxslider() {
    if ($('.mobile-only-bxslider').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereChangePhone() {
    if ($('.btn-changePhone').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereChangeTextBxslider() {
    if ($('.change-text-slider').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereAccordion() {
    if ($('.widget__accordion').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereSupport() {
    if ($('.support-dropdown').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereGallery() {
    if ($('.gallery-widget').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereOrderedList() {
    if ($('.bullet-wrapper .bullet-container ol').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereImportantMessage() {
    if ($('.important-msg').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereFull() {
    if ($('.full-width').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isTherePersonalLanding() {
    if ($('.js-isotope').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isTherePlanDetails() {
    if ($('.plan-details').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereDataBinding () {
    if ($('[data-binding]').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereLoginMenu () {
    if ($('.search-login-block.post-login').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereVideoPanel() {
    if ($('.video-panel').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereTileBackstretch() {
   if ($('.cards-img-bg').length != 0) {
       return true;
   } else {
       return false;
   }
}

function isThereVideoSlide () {
    if ($('.slider-video-wrapper').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereFreakingScroll() {
    if ($('#sticky-mobile').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereLink() {
    if ($('.select-picker.mobile-tab-nav').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereGreyBG() {
    if ($('.mobilepromoindiv_v1 section .tstm-grey').length != 0) {
        return true;
    } else {
        return false;
    }
}

function isThereTwoTileLinks() {
    if ($('.noShadow').length != 0) {
        return true;
    } else {
        return false;
    }
}