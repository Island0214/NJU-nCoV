<template>
    <div>
        <br>
        <el-row>
            
            
            <el-col :span="7">
                <div class="el-card box-card is-always-shadow">
                    <div class="el-card__header">
                        <div class="clearfix">
                            <h1>SEIR模型参数</h1>
                        </div>
                    </div>
                    <div class="el-card__body">
                        <!-- 初期无感染者S, 0-10000 -->
                        <h4>初期无感染者 S = {{Sinit}}</h4>
                        <div class="block">
                            <el-slider v-model="Sinit" :step="1000"  :marks="mark1" :min="0" :max="10000"> </el-slider>
                        </div>
                        <br>
                        <!-- 初期感染者 I, 0-100 -->
                        <h4>初期感染者 I = {{Iinit}}</h4>
                        <div class="block">
                            <el-slider v-model="Iinit" :step="1"  :marks="mark2" :min="0" :max="100"> </el-slider>
                        </div>
                        <br>
                        <!-- 基本传染数 R0, 0-20 -->
                        <h4>基本传染数 R0 = {{R0}}</h4>
                        <div class="block">
                            <el-slider v-model="R0" :step="0.001"  :marks="mark3" :min="0" :max="20"> </el-slider>
                        </div>
                        <br>
                        <!-- 潜伏周期 lp, 0-21天 -->
                        <h4>潜伏周期 lp = {{lp}}</h4>
                        <div class="block">
                            <el-slider v-model="lp" :step="1"  :marks="mark4" :min="0" :max="21"> </el-slider>
                        </div>
                        <br>
                        <!-- 发病周期 ip, 0-32天 -->
                        <h4>发病周期 ip = {{ip}}</h4>
                        <div class="block">
                            <el-slider v-model="ip" :step="1"  :marks="mark4" :min="0" :max="21"> </el-slider>
                        </div>
                        <br>
                        <!-- 检查后隔离比率 T, 0-100% -->
                        <h4>检查后隔离比率 T = {{T}}</h4>
                        <div class="block">
                            <el-slider v-model="T" :step="0.01"  :marks="mark6" :min="0" :max="1"> </el-slider>
                        </div>
                        <br>

                        <h3>感染率 β: {{beta}}</h3>
                        <h3>治愈率 γ: {{gamma}}</h3>
                    </div>
                </div>
                
            </el-col>

            <el-col :span="1"><br></el-col>

            <el-col :span="16" style="text-align: center">

                <h2>当前R0系数值为：{{R0}}</h2>
                <br>

                <div id="container" class="chart-wrapper"> </div>

                <el-divider></el-divider>

                <div class="text-wrapper">
                    <p>在没有任何干预的情况下，模拟单个人群中COVID-19流行的自然过程，采用SEIR模型。</p>
                    <p><span class="MathJax_SVG" tabindex="-1" style="font-size: 100%; display: inline-block;"><svg xmlns:xlink="http://www.w3.org/1999/xlink" width="2.816ex" height="2.429ex" viewBox="0 -783.2 1212.6 1045.7" role="img" focusable="false" style="vertical-align: -0.609ex;"><defs><path stroke-width="0" id="E2-MJMATHI-52" d="M230 637Q203 637 198 638T193 649Q193 676 204 682Q206 683 378 683Q550 682 564 680Q620 672 658 652T712 606T733 563T739 529Q739 484 710 445T643 385T576 351T538 338L545 333Q612 295 612 223Q612 212 607 162T602 80V71Q602 53 603 43T614 25T640 16Q668 16 686 38T712 85Q717 99 720 102T735 105Q755 105 755 93Q755 75 731 36Q693 -21 641 -21H632Q571 -21 531 4T487 82Q487 109 502 166T517 239Q517 290 474 313Q459 320 449 321T378 323H309L277 193Q244 61 244 59Q244 55 245 54T252 50T269 48T302 46H333Q339 38 339 37T336 19Q332 6 326 0H311Q275 2 180 2Q146 2 117 2T71 2T50 1Q33 1 33 10Q33 12 36 24Q41 43 46 45Q50 46 61 46H67Q94 46 127 49Q141 52 146 61Q149 65 218 339T287 628Q287 635 230 637ZM630 554Q630 586 609 608T523 636Q521 636 500 636T462 637H440Q393 637 386 627Q385 624 352 494T319 361Q319 360 388 360Q466 361 492 367Q556 377 592 426Q608 449 619 486T630 554Z"></path><path stroke-width="0" id="E2-MJMAIN-30" d="M96 585Q152 666 249 666Q297 666 345 640T423 548Q460 465 460 320Q460 165 417 83Q397 41 362 16T301 -15T250 -22Q224 -22 198 -16T137 16T82 83Q39 165 39 320Q39 494 96 585ZM321 597Q291 629 250 629Q208 629 178 597Q153 571 145 525T137 333Q137 175 145 125T181 46Q209 16 250 16Q290 16 318 46Q347 76 354 130T362 333Q362 478 354 524T321 597Z"></path></defs><g stroke="currentColor" fill="currentColor" stroke-width="0" transform="matrix(1 0 0 -1 0 0)"><use xlink:href="#E2-MJMATHI-52" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E2-MJMAIN-30" x="1073" y="-213"></use></g></svg></span><script type="math/tex">R_0</script>是以下各项的总和</p>
                    <ol class="ol-wrapper">
                    <li>个人在<span class="MathJax_SVG" tabindex="-1" style="font-size: 100%; display: inline-block;"><svg xmlns:xlink="http://www.w3.org/1999/xlink" width="2.075ex" height="2.308ex" viewBox="0 -783.2 893.6 993.6" role="img" focusable="false" style="vertical-align: -0.489ex;"><defs><path stroke-width="0" id="E3-MJMATHI-49" d="M43 1Q26 1 26 10Q26 12 29 24Q34 43 39 45Q42 46 54 46H60Q120 46 136 53Q137 53 138 54Q143 56 149 77T198 273Q210 318 216 344Q286 624 286 626Q284 630 284 631Q274 637 213 637H193Q184 643 189 662Q193 677 195 680T209 683H213Q285 681 359 681Q481 681 487 683H497Q504 676 504 672T501 655T494 639Q491 637 471 637Q440 637 407 634Q393 631 388 623Q381 609 337 432Q326 385 315 341Q245 65 245 59Q245 52 255 50T307 46H339Q345 38 345 37T342 19Q338 6 332 0H316Q279 2 179 2Q143 2 113 2T65 2T43 1Z"></path><path stroke-width="0" id="E3-MJMAIN-31" d="M213 578L200 573Q186 568 160 563T102 556H83V602H102Q149 604 189 617T245 641T273 663Q275 666 285 666Q294 666 302 660V361L303 61Q310 54 315 52T339 48T401 46H427V0H416Q395 3 257 3Q121 3 100 0H88V46H114Q136 46 152 46T177 47T193 50T201 52T207 57T213 61V578Z"></path></defs><g stroke="currentColor" fill="currentColor" stroke-width="0" transform="matrix(1 0 0 -1 0 0)"><use xlink:href="#E3-MJMATHI-49" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E3-MJMAIN-31" x="622" y="-213"></use></g></svg></span><script type="math/tex">I_1</script>阶段产生的平均二次感染数</li>
                    <li>受感染的个人发展到<span class="MathJax_SVG" tabindex="-1" style="font-size: 100%; display: inline-block;"><svg xmlns:xlink="http://www.w3.org/1999/xlink" width="2.075ex" height="2.308ex" viewBox="0 -783.2 893.6 993.6" role="img" focusable="false" style="vertical-align: -0.489ex;"><defs><path stroke-width="0" id="E5-MJMATHI-49" d="M43 1Q26 1 26 10Q26 12 29 24Q34 43 39 45Q42 46 54 46H60Q120 46 136 53Q137 53 138 54Q143 56 149 77T198 273Q210 318 216 344Q286 624 286 626Q284 630 284 631Q274 637 213 637H193Q184 643 189 662Q193 677 195 680T209 683H213Q285 681 359 681Q481 681 487 683H497Q504 676 504 672T501 655T494 639Q491 637 471 637Q440 637 407 634Q393 631 388 623Q381 609 337 432Q326 385 315 341Q245 65 245 59Q245 52 255 50T307 46H339Q345 38 345 37T342 19Q338 6 332 0H316Q279 2 179 2Q143 2 113 2T65 2T43 1Z"></path><path stroke-width="0" id="E5-MJMAIN-32" d="M109 429Q82 429 66 447T50 491Q50 562 103 614T235 666Q326 666 387 610T449 465Q449 422 429 383T381 315T301 241Q265 210 201 149L142 93L218 92Q375 92 385 97Q392 99 409 186V189H449V186Q448 183 436 95T421 3V0H50V19V31Q50 38 56 46T86 81Q115 113 136 137Q145 147 170 174T204 211T233 244T261 278T284 308T305 340T320 369T333 401T340 431T343 464Q343 527 309 573T212 619Q179 619 154 602T119 569T109 550Q109 549 114 549Q132 549 151 535T170 489Q170 464 154 447T109 429Z"></path></defs><g stroke="currentColor" fill="currentColor" stroke-width="0" transform="matrix(1 0 0 -1 0 0)"><use xlink:href="#E5-MJMATHI-49" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E5-MJMAIN-32" x="622" y="-213"></use></g></svg></span><script type="math/tex">I_2</script>的概率乘以该个人在<span class="MathJax_SVG" tabindex="-1" style="font-size: 100%; display: inline-block;"><svg xmlns:xlink="http://www.w3.org/1999/xlink" width="2.075ex" height="2.308ex" viewBox="0 -783.2 893.6 993.6" role="img" focusable="false" style="vertical-align: -0.489ex;"><defs><path stroke-width="0" id="E5-MJMATHI-49" d="M43 1Q26 1 26 10Q26 12 29 24Q34 43 39 45Q42 46 54 46H60Q120 46 136 53Q137 53 138 54Q143 56 149 77T198 273Q210 318 216 344Q286 624 286 626Q284 630 284 631Q274 637 213 637H193Q184 643 189 662Q193 677 195 680T209 683H213Q285 681 359 681Q481 681 487 683H497Q504 676 504 672T501 655T494 639Q491 637 471 637Q440 637 407 634Q393 631 388 623Q381 609 337 432Q326 385 315 341Q245 65 245 59Q245 52 255 50T307 46H339Q345 38 345 37T342 19Q338 6 332 0H316Q279 2 179 2Q143 2 113 2T65 2T43 1Z"></path><path stroke-width="0" id="E5-MJMAIN-32" d="M109 429Q82 429 66 447T50 491Q50 562 103 614T235 666Q326 666 387 610T449 465Q449 422 429 383T381 315T301 241Q265 210 201 149L142 93L218 92Q375 92 385 97Q392 99 409 186V189H449V186Q448 183 436 95T421 3V0H50V19V31Q50 38 56 46T86 81Q115 113 136 137Q145 147 170 174T204 211T233 244T261 278T284 308T305 340T320 369T333 401T340 431T343 464Q343 527 309 573T212 619Q179 619 154 602T119 569T109 550Q109 549 114 549Q132 549 151 535T170 489Q170 464 154 447T109 429Z"></path></defs><g stroke="currentColor" fill="currentColor" stroke-width="0" transform="matrix(1 0 0 -1 0 0)"><use xlink:href="#E5-MJMATHI-49" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E5-MJMAIN-32" x="622" y="-213"></use></g></svg></span><script type="math/tex">I_2</script>阶段产生的二次感染的平均数量</li>
                    <li>被感染的个体发展到<span class="MathJax_SVG" tabindex="-1" style="font-size: 100%; display: inline-block;"><svg xmlns:xlink="http://www.w3.org/1999/xlink" width="2.075ex" height="2.429ex" viewBox="0 -783.2 893.6 1045.7" role="img" focusable="false" style="vertical-align: -0.609ex;"><defs><path stroke-width="0" id="E7-MJMATHI-49" d="M43 1Q26 1 26 10Q26 12 29 24Q34 43 39 45Q42 46 54 46H60Q120 46 136 53Q137 53 138 54Q143 56 149 77T198 273Q210 318 216 344Q286 624 286 626Q284 630 284 631Q274 637 213 637H193Q184 643 189 662Q193 677 195 680T209 683H213Q285 681 359 681Q481 681 487 683H497Q504 676 504 672T501 655T494 639Q491 637 471 637Q440 637 407 634Q393 631 388 623Q381 609 337 432Q326 385 315 341Q245 65 245 59Q245 52 255 50T307 46H339Q345 38 345 37T342 19Q338 6 332 0H316Q279 2 179 2Q143 2 113 2T65 2T43 1Z"></path><path stroke-width="0" id="E7-MJMAIN-33" d="M127 463Q100 463 85 480T69 524Q69 579 117 622T233 665Q268 665 277 664Q351 652 390 611T430 522Q430 470 396 421T302 350L299 348Q299 347 308 345T337 336T375 315Q457 262 457 175Q457 96 395 37T238 -22Q158 -22 100 21T42 130Q42 158 60 175T105 193Q133 193 151 175T169 130Q169 119 166 110T159 94T148 82T136 74T126 70T118 67L114 66Q165 21 238 21Q293 21 321 74Q338 107 338 175V195Q338 290 274 322Q259 328 213 329L171 330L168 332Q166 335 166 348Q166 366 174 366Q202 366 232 371Q266 376 294 413T322 525V533Q322 590 287 612Q265 626 240 626Q208 626 181 615T143 592T132 580H135Q138 579 143 578T153 573T165 566T175 555T183 540T186 520Q186 498 172 481T127 463Z"></path></defs><g stroke="currentColor" fill="currentColor" stroke-width="0" transform="matrix(1 0 0 -1 0 0)"><use xlink:href="#E7-MJMATHI-49" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E7-MJMAIN-33" x="622" y="-213"></use></g></svg></span><script type="math/tex">I_3</script>的概率乘以个体在阶段<span class="MathJax_SVG" tabindex="-1" style="font-size: 100%; display: inline-block;"><svg xmlns:xlink="http://www.w3.org/1999/xlink" width="2.075ex" height="2.429ex" viewBox="0 -783.2 893.6 1045.7" role="img" focusable="false" style="vertical-align: -0.609ex;"><defs><path stroke-width="0" id="E7-MJMATHI-49" d="M43 1Q26 1 26 10Q26 12 29 24Q34 43 39 45Q42 46 54 46H60Q120 46 136 53Q137 53 138 54Q143 56 149 77T198 273Q210 318 216 344Q286 624 286 626Q284 630 284 631Q274 637 213 637H193Q184 643 189 662Q193 677 195 680T209 683H213Q285 681 359 681Q481 681 487 683H497Q504 676 504 672T501 655T494 639Q491 637 471 637Q440 637 407 634Q393 631 388 623Q381 609 337 432Q326 385 315 341Q245 65 245 59Q245 52 255 50T307 46H339Q345 38 345 37T342 19Q338 6 332 0H316Q279 2 179 2Q143 2 113 2T65 2T43 1Z"></path><path stroke-width="0" id="E7-MJMAIN-33" d="M127 463Q100 463 85 480T69 524Q69 579 117 622T233 665Q268 665 277 664Q351 652 390 611T430 522Q430 470 396 421T302 350L299 348Q299 347 308 345T337 336T375 315Q457 262 457 175Q457 96 395 37T238 -22Q158 -22 100 21T42 130Q42 158 60 175T105 193Q133 193 151 175T169 130Q169 119 166 110T159 94T148 82T136 74T126 70T118 67L114 66Q165 21 238 21Q293 21 321 74Q338 107 338 175V195Q338 290 274 322Q259 328 213 329L171 330L168 332Q166 335 166 348Q166 366 174 366Q202 366 232 371Q266 376 294 413T322 525V533Q322 590 287 612Q265 626 240 626Q208 626 181 615T143 592T132 580H135Q138 579 143 578T153 573T165 566T175 555T183 540T186 520Q186 498 172 481T127 463Z"></path></defs><g stroke="currentColor" fill="currentColor" stroke-width="0" transform="matrix(1 0 0 -1 0 0)"><use xlink:href="#E7-MJMATHI-49" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E7-MJMAIN-33" x="622" y="-213"></use></g></svg></span><script type="math/tex">I_3</script>中产生的二次感染的平均数量</li>

                    </ol>
                    <p><span class="MathJax_SVG" tabindex="-1" style="font-size: 100%; display: inline-block;"><svg xmlns:xlink="http://www.w3.org/1999/xlink" width="57.767ex" height="12.313ex" viewBox="0 -2918.7 24871.6 5301.6" role="img" focusable="false" style="vertical-align: -5.535ex;"><defs><path stroke-width="0" id="E8-MJMATHI-52" d="M230 637Q203 637 198 638T193 649Q193 676 204 682Q206 683 378 683Q550 682 564 680Q620 672 658 652T712 606T733 563T739 529Q739 484 710 445T643 385T576 351T538 338L545 333Q612 295 612 223Q612 212 607 162T602 80V71Q602 53 603 43T614 25T640 16Q668 16 686 38T712 85Q717 99 720 102T735 105Q755 105 755 93Q755 75 731 36Q693 -21 641 -21H632Q571 -21 531 4T487 82Q487 109 502 166T517 239Q517 290 474 313Q459 320 449 321T378 323H309L277 193Q244 61 244 59Q244 55 245 54T252 50T269 48T302 46H333Q339 38 339 37T336 19Q332 6 326 0H311Q275 2 180 2Q146 2 117 2T71 2T50 1Q33 1 33 10Q33 12 36 24Q41 43 46 45Q50 46 61 46H67Q94 46 127 49Q141 52 146 61Q149 65 218 339T287 628Q287 635 230 637ZM630 554Q630 586 609 608T523 636Q521 636 500 636T462 637H440Q393 637 386 627Q385 624 352 494T319 361Q319 360 388 360Q466 361 492 367Q556 377 592 426Q608 449 619 486T630 554Z"></path><path stroke-width="0" id="E8-MJMAIN-30" d="M96 585Q152 666 249 666Q297 666 345 640T423 548Q460 465 460 320Q460 165 417 83Q397 41 362 16T301 -15T250 -22Q224 -22 198 -16T137 16T82 83Q39 165 39 320Q39 494 96 585ZM321 597Q291 629 250 629Q208 629 178 597Q153 571 145 525T137 333Q137 175 145 125T181 46Q209 16 250 16Q290 16 318 46Q347 76 354 130T362 333Q362 478 354 524T321 597Z"></path><path stroke-width="0" id="E8-MJMAIN-3D" d="M56 347Q56 360 70 367H707Q722 359 722 347Q722 336 708 328L390 327H72Q56 332 56 347ZM56 153Q56 168 72 173H708Q722 163 722 153Q722 140 707 133H70Q56 140 56 153Z"></path><path stroke-width="0" id="E8-MJMATHI-4E" d="M234 637Q231 637 226 637Q201 637 196 638T191 649Q191 676 202 682Q204 683 299 683Q376 683 387 683T401 677Q612 181 616 168L670 381Q723 592 723 606Q723 633 659 637Q635 637 635 648Q635 650 637 660Q641 676 643 679T653 683Q656 683 684 682T767 680Q817 680 843 681T873 682Q888 682 888 672Q888 650 880 642Q878 637 858 637Q787 633 769 597L620 7Q618 0 599 0Q585 0 582 2Q579 5 453 305L326 604L261 344Q196 88 196 79Q201 46 268 46H278Q284 41 284 38T282 19Q278 6 272 0H259Q228 2 151 2Q123 2 100 2T63 2T46 1Q31 1 31 10Q31 14 34 26T39 40Q41 46 62 46Q130 49 150 85Q154 91 221 362L289 634Q287 635 234 637Z"></path><path stroke-width="0" id="E8-MJMATHI-3B2" d="M29 -194Q23 -188 23 -186Q23 -183 102 134T186 465Q208 533 243 584T309 658Q365 705 429 705H431Q493 705 533 667T573 570Q573 465 469 396L482 383Q533 332 533 252Q533 139 448 65T257 -10Q227 -10 203 -2T165 17T143 40T131 59T126 65L62 -188Q60 -194 42 -194H29ZM353 431Q392 431 427 419L432 422Q436 426 439 429T449 439T461 453T472 471T484 495T493 524T501 560Q503 569 503 593Q503 611 502 616Q487 667 426 667Q384 667 347 643T286 582T247 514T224 455Q219 439 186 308T152 168Q151 163 151 147Q151 99 173 68Q204 26 260 26Q302 26 349 51T425 137Q441 171 449 214T457 279Q457 337 422 372Q380 358 347 358H337Q258 358 258 389Q258 396 261 403Q275 431 353 431Z"></path><path stroke-width="0" id="E8-MJMAIN-31" d="M213 578L200 573Q186 568 160 563T102 556H83V602H102Q149 604 189 617T245 641T273 663Q275 666 285 666Q294 666 302 660V361L303 61Q310 54 315 52T339 48T401 46H427V0H416Q395 3 257 3Q121 3 100 0H88V46H114Q136 46 152 46T177 47T193 50T201 52T207 57T213 61V578Z"></path><path stroke-width="0" id="E8-MJMATHI-70" d="M23 287Q24 290 25 295T30 317T40 348T55 381T75 411T101 433T134 442Q209 442 230 378L240 387Q302 442 358 442Q423 442 460 395T497 281Q497 173 421 82T249 -10Q227 -10 210 -4Q199 1 187 11T168 28L161 36Q160 35 139 -51T118 -138Q118 -144 126 -145T163 -148H188Q194 -155 194 -157T191 -175Q188 -187 185 -190T172 -194Q170 -194 161 -194T127 -193T65 -192Q-5 -192 -24 -194H-32Q-39 -187 -39 -183Q-37 -156 -26 -148H-6Q28 -147 33 -136Q36 -130 94 103T155 350Q156 355 156 364Q156 405 131 405Q109 405 94 377T71 316T59 280Q57 278 43 278H29Q23 284 23 287ZM178 102Q200 26 252 26Q282 26 310 49T356 107Q374 141 392 215T411 325V331Q411 405 350 405Q339 405 328 402T306 393T286 380T269 365T254 350T243 336T235 326L232 322Q232 321 229 308T218 264T204 212Q178 106 178 102Z"></path><path stroke-width="0" id="E8-MJMAIN-2B" d="M56 237T56 250T70 270H369V420L370 570Q380 583 389 583Q402 583 409 568V270H707Q722 262 722 250T707 230H409V-68Q401 -82 391 -82H389H387Q375 -82 369 -68V230H70Q56 237 56 250Z"></path><path stroke-width="0" id="E8-MJMATHI-3B3" d="M31 249Q11 249 11 258Q11 275 26 304T66 365T129 418T206 441Q233 441 239 440Q287 429 318 386T371 255Q385 195 385 170Q385 166 386 166L398 193Q418 244 443 300T486 391T508 430Q510 431 524 431H537Q543 425 543 422Q543 418 522 378T463 251T391 71Q385 55 378 6T357 -100Q341 -165 330 -190T303 -216Q286 -216 286 -188Q286 -138 340 32L346 51L347 69Q348 79 348 100Q348 257 291 317Q251 355 196 355Q148 355 108 329T51 260Q49 251 47 251Q45 249 31 249Z"></path><path stroke-width="0" id="E8-MJMAIN-28" d="M94 250Q94 319 104 381T127 488T164 576T202 643T244 695T277 729T302 750H315H319Q333 750 333 741Q333 738 316 720T275 667T226 581T184 443T167 250T184 58T225 -81T274 -167T316 -220T333 -241Q333 -250 318 -250H315H302L274 -226Q180 -141 137 -14T94 250Z"></path><path stroke-width="0" id="E8-MJMAIN-32" d="M109 429Q82 429 66 447T50 491Q50 562 103 614T235 666Q326 666 387 610T449 465Q449 422 429 383T381 315T301 241Q265 210 201 149L142 93L218 92Q375 92 385 97Q392 99 409 186V189H449V186Q448 183 436 95T421 3V0H50V19V31Q50 38 56 46T86 81Q115 113 136 137Q145 147 170 174T204 211T233 244T261 278T284 308T305 340T320 369T333 401T340 431T343 464Q343 527 309 573T212 619Q179 619 154 602T119 569T109 550Q109 549 114 549Q132 549 151 535T170 489Q170 464 154 447T109 429Z"></path><path stroke-width="0" id="E8-MJMAIN-33" d="M127 463Q100 463 85 480T69 524Q69 579 117 622T233 665Q268 665 277 664Q351 652 390 611T430 522Q430 470 396 421T302 350L299 348Q299 347 308 345T337 336T375 315Q457 262 457 175Q457 96 395 37T238 -22Q158 -22 100 21T42 130Q42 158 60 175T105 193Q133 193 151 175T169 130Q169 119 166 110T159 94T148 82T136 74T126 70T118 67L114 66Q165 21 238 21Q293 21 321 74Q338 107 338 175V195Q338 290 274 322Q259 328 213 329L171 330L168 332Q166 335 166 348Q166 366 174 366Q202 366 232 371Q266 376 294 413T322 525V533Q322 590 287 612Q265 626 240 626Q208 626 181 615T143 592T132 580H135Q138 579 143 578T153 573T165 566T175 555T183 540T186 520Q186 498 172 481T127 463Z"></path><path stroke-width="0" id="E8-MJMATHI-3BC" d="M58 -216Q44 -216 34 -208T23 -186Q23 -176 96 116T173 414Q186 442 219 442Q231 441 239 435T249 423T251 413Q251 401 220 279T187 142Q185 131 185 107V99Q185 26 252 26Q261 26 270 27T287 31T302 38T315 45T327 55T338 65T348 77T356 88T365 100L372 110L408 253Q444 395 448 404Q461 431 491 431Q504 431 512 424T523 412T525 402L449 84Q448 79 448 68Q448 43 455 35T476 26Q485 27 496 35Q517 55 537 131Q543 151 547 152Q549 153 557 153H561Q580 153 580 144Q580 138 575 117T555 63T523 13Q510 0 491 -8Q483 -10 467 -10Q446 -10 429 -4T402 11T385 29T376 44T374 51L368 45Q362 39 350 30T324 12T288 -4T246 -11Q199 -11 153 12L129 -85Q108 -167 104 -180T92 -202Q76 -216 58 -216Z"></path><path stroke-width="0" id="E8-MJMAIN-29" d="M60 749L64 750Q69 750 74 750H86L114 726Q208 641 251 514T294 250Q294 182 284 119T261 12T224 -76T186 -143T145 -194T113 -227T90 -246Q87 -249 86 -250H74Q66 -250 63 -250T58 -247T55 -238Q56 -237 66 -225Q221 -64 221 250T66 725Q56 737 55 738Q55 746 60 749Z"></path><path stroke-width="0" id="E8-MJSZ3-28" d="M701 -940Q701 -943 695 -949H664Q662 -947 636 -922T591 -879T537 -818T475 -737T412 -636T350 -511T295 -362T250 -186T221 17T209 251Q209 962 573 1361Q596 1386 616 1405T649 1437T664 1450H695Q701 1444 701 1441Q701 1436 681 1415T629 1356T557 1261T476 1118T400 927T340 675T308 359Q306 321 306 250Q306 -139 400 -430T690 -924Q701 -936 701 -940Z"></path><path stroke-width="0" id="E8-MJSZ3-29" d="M34 1438Q34 1446 37 1448T50 1450H56H71Q73 1448 99 1423T144 1380T198 1319T260 1238T323 1137T385 1013T440 864T485 688T514 485T526 251Q526 134 519 53Q472 -519 162 -860Q139 -885 119 -904T86 -936T71 -949H56Q43 -949 39 -947T34 -937Q88 -883 140 -813Q428 -430 428 251Q428 453 402 628T338 922T245 1146T145 1309T46 1425Q44 1427 42 1429T39 1433T36 1436L34 1438Z"></path></defs><g stroke="currentColor" fill="currentColor" stroke-width="0" transform="matrix(1 0 0 -1 0 0)"><g transform="translate(167,0)"><g transform="translate(-13,0)"><g transform="translate(0,1349)"><use xlink:href="#E8-MJMATHI-52" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-30" x="1073" y="-213"></use></g></g><g transform="translate(1199,0)"><g transform="translate(0,1349)"><use xlink:href="#E8-MJMAIN-3D" x="277" y="0"></use><use xlink:href="#E8-MJMATHI-4E" x="1333" y="0"></use><g transform="translate(2221,0)"><g transform="translate(120,0)"><rect stroke="none" width="3270" height="60" x="0" y="220"></rect><g transform="translate(1125,682)"><use xlink:href="#E8-MJMATHI-3B2" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-31" x="800" y="-213"></use></g><g transform="translate(60,-686)"><use xlink:href="#E8-MJMATHI-70" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-31" x="711" y="-213"></use><use xlink:href="#E8-MJMAIN-2B" x="1178" y="0"></use><g transform="translate(2178,0)"><use xlink:href="#E8-MJMATHI-3B3" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-31" x="732" y="-213"></use></g></g></g></g><use xlink:href="#E8-MJMAIN-2B" x="5954" y="0"></use><g transform="translate(6732,0)"><g transform="translate(342,0)"><rect stroke="none" width="3270" height="60" x="0" y="220"></rect><g transform="translate(1156,682)"><use xlink:href="#E8-MJMATHI-70" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-31" x="711" y="-213"></use></g><g transform="translate(60,-686)"><use xlink:href="#E8-MJMATHI-70" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-31" x="711" y="-213"></use><use xlink:href="#E8-MJMAIN-2B" x="1178" y="0"></use><g transform="translate(2178,0)"><use xlink:href="#E8-MJMATHI-3B3" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-31" x="732" y="-213"></use></g></g></g></g><g transform="translate(10465,0)"><use xlink:href="#E8-MJSZ3-28"></use><g transform="translate(736,0)"><g transform="translate(120,0)"><rect stroke="none" width="3270" height="60" x="0" y="220"></rect><g transform="translate(681,682)"><use xlink:href="#E8-MJMATHI-4E" x="0" y="0"></use><g transform="translate(888,0)"><use xlink:href="#E8-MJMATHI-3B2" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-32" x="800" y="-213"></use></g></g><g transform="translate(60,-686)"><use xlink:href="#E8-MJMATHI-70" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-32" x="711" y="-213"></use><use xlink:href="#E8-MJMAIN-2B" x="1178" y="0"></use><g transform="translate(2178,0)"><use xlink:href="#E8-MJMATHI-3B3" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-32" x="732" y="-213"></use></g></g></g></g><use xlink:href="#E8-MJMAIN-2B" x="4468" y="0"></use><g transform="translate(5246,0)"><g transform="translate(342,0)"><rect stroke="none" width="3270" height="60" x="0" y="220"></rect><g transform="translate(1156,682)"><use xlink:href="#E8-MJMATHI-70" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-32" x="711" y="-213"></use></g><g transform="translate(60,-686)"><use xlink:href="#E8-MJMATHI-70" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-32" x="711" y="-213"></use><use xlink:href="#E8-MJMAIN-2B" x="1178" y="0"></use><g transform="translate(2178,0)"><use xlink:href="#E8-MJMATHI-3B3" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-32" x="732" y="-213"></use></g></g></g></g><g transform="translate(8979,0)"><g transform="translate(120,0)"><rect stroke="none" width="2916" height="60" x="0" y="220"></rect><g transform="translate(504,682)"><use xlink:href="#E8-MJMATHI-4E" x="0" y="0"></use><g transform="translate(888,0)"><use xlink:href="#E8-MJMATHI-3B2" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-33" x="800" y="-213"></use></g></g><g transform="translate(60,-686)"><use xlink:href="#E8-MJMATHI-3BC" x="0" y="0"></use><use xlink:href="#E8-MJMAIN-2B" x="825" y="0"></use><g transform="translate(1825,0)"><use xlink:href="#E8-MJMATHI-3B3" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-33" x="732" y="-213"></use></g></g></g></g><use xlink:href="#E8-MJSZ3-29" x="12136" y="-1"></use></g></g><g transform="translate(0,-1350)"><use xlink:href="#E8-MJMAIN-3D" x="277" y="0"></use><use xlink:href="#E8-MJMATHI-4E" x="1333" y="0"></use><g transform="translate(2221,0)"><g transform="translate(120,0)"><rect stroke="none" width="3270" height="60" x="0" y="220"></rect><g transform="translate(1125,682)"><use xlink:href="#E8-MJMATHI-3B2" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-31" x="800" y="-213"></use></g><g transform="translate(60,-686)"><use xlink:href="#E8-MJMATHI-70" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-31" x="711" y="-213"></use><use xlink:href="#E8-MJMAIN-2B" x="1178" y="0"></use><g transform="translate(2178,0)"><use xlink:href="#E8-MJMATHI-3B3" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-31" x="732" y="-213"></use></g></g></g></g><g transform="translate(5732,0)"><use xlink:href="#E8-MJSZ3-28"></use><use xlink:href="#E8-MJMAIN-31" x="736" y="0"></use><use xlink:href="#E8-MJMAIN-2B" x="1458" y="0"></use><g transform="translate(2236,0)"><g transform="translate(342,0)"><rect stroke="none" width="3270" height="60" x="0" y="220"></rect><g transform="translate(1156,682)"><use xlink:href="#E8-MJMATHI-70" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-31" x="711" y="-213"></use></g><g transform="translate(60,-686)"><use xlink:href="#E8-MJMATHI-70" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-32" x="711" y="-213"></use><use xlink:href="#E8-MJMAIN-2B" x="1178" y="0"></use><g transform="translate(2178,0)"><use xlink:href="#E8-MJMATHI-3B3" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-32" x="732" y="-213"></use></g></g></g></g><g transform="translate(5968,0)"><g transform="translate(120,0)"><rect stroke="none" width="1139" height="60" x="0" y="220"></rect><g transform="translate(60,682)"><use xlink:href="#E8-MJMATHI-3B2" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-32" x="800" y="-213"></use></g><g transform="translate(60,-694)"><use xlink:href="#E8-MJMATHI-3B2" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-31" x="800" y="-213"></use></g></g></g><g transform="translate(7348,0)"><use xlink:href="#E8-MJSZ3-28"></use><use xlink:href="#E8-MJMAIN-31" x="736" y="0"></use><use xlink:href="#E8-MJMAIN-2B" x="1458" y="0"></use><g transform="translate(2236,0)"><g transform="translate(342,0)"><rect stroke="none" width="2916" height="60" x="0" y="220"></rect><g transform="translate(980,682)"><use xlink:href="#E8-MJMATHI-70" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-32" x="711" y="-213"></use></g><g transform="translate(60,-686)"><use xlink:href="#E8-MJMATHI-3BC" x="0" y="0"></use><use xlink:href="#E8-MJMAIN-2B" x="825" y="0"></use><g transform="translate(1825,0)"><use xlink:href="#E8-MJMATHI-3B3" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-33" x="732" y="-213"></use></g></g></g></g><g transform="translate(5615,0)"><g transform="translate(120,0)"><rect stroke="none" width="1139" height="60" x="0" y="220"></rect><g transform="translate(60,682)"><use xlink:href="#E8-MJMATHI-3B2" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-33" x="800" y="-213"></use></g><g transform="translate(60,-694)"><use xlink:href="#E8-MJMATHI-3B2" x="0" y="0"></use><use transform="scale(0.707)" xlink:href="#E8-MJMAIN-32" x="800" y="-213"></use></g></g></g><use xlink:href="#E8-MJSZ3-29" x="6994" y="-1"></use></g><use xlink:href="#E8-MJSZ3-29" x="15079" y="-1"></use></g></g></g></g></g></svg></span><script type="math/tex">\begin{equation}
                    \begin{split}
                    R_0 & = N\frac{\beta_1}{p_1+\gamma_1} + \frac{p_1}{p_1 + \gamma_1} \left( \frac{N \beta_2}{p_2+\gamma_2} + \frac{p_2}{p_2 + \gamma_2} \frac{N \beta_3}{\mu+\gamma_3}\right)\\
                    &= N\frac{\beta_1}{p_1+\gamma_1} \left(1 + \frac{p_1}{p_2 + \gamma_2}\frac{\beta_2}{\beta_1} \left( 1 + \frac{p_2}{\mu + \gamma_3} \frac{\beta_3}{\beta_2} \right) \right)
                    \end{split}
                    \end{equation}</script></p>
                    <p>&nbsp;</p>
                    <br>
                    <p>时间所限，这里实现的模型较为简单，更加优秀的模型可视化可以参考项目<a target="_blank" href="https://github.com/alsnhll/SEIR_COVID19">SEIR_COVID19</a></p>
                </div>

            </el-col>

        </el-row>
        
        <Footer></Footer>
    </div>

</template>

<script>

// import LineChart from '../components/charts/LineChart.vue'
import Footer from '../components/Footer.vue'
import echarts from "echarts";

export default {
    name: 'Model',
    components: {
        // LineChart,
        Footer
    },
    data() {
        return {
            chartParams: {
                
            },
            mark1: {
                0: '0',
                10000: '10000'
            },
            mark2: {
                0: '0',
                100: '100'
            },
            mark3: {
                0: '0',
                20: '20'
            },
            mark4: {
                0: '0day',
                21: '21day'
            },
            mark6: {
                0: '0%',
                1: '100%'
            },
            Sinit: 1000,    // 初始无免疫者, (总数)
            Einit: 0,       // 潜伏期人群
            Iinit: 5,       // 初始感染者人群
            Rinit: 0,       // 恢复者人群

            R0: 10,         // 基本传染数
            lp: 14,         // 潜伏周期
            ip: 7,          // 发病周期
            T: 0.01,        // 检查隔离率
            D: 20,          // 恢复周期

            alpha: 1.0/this.lp,     // 潜伏率
            gamma: 1.0/this.ip,     // 恢复率
            beta: this.R0*this.gamma,      // 感染率
            
            lst: [],        // 数据

            chart: null,

        }
    },
    watch: {
        // 对可变参数进行相应
        lp: function(value){
            this.alpha = (1.0/value).toFixed(4);
            this.updateData();
        },
        ip: function(value) {
            this.gamma = (1.0/value).toFixed(4);
            this.updateData();
        },
        R0: function(value) {
            this.beta = (value*this.gamma).toFixed(4);
            this.updateData();
        },
        Sinit: function(value) {
            console.log("Sinit, ", value)
            var dom = document.getElementById("container");
            var myChart = echarts.init(dom);
            myChart.setOption({
                dataZoom: [{
                    show: true,
                    type: 'inside',
                    filterMode: 'none',
                    xAxisIndex: [0],
                    startValue: 0,
                    endValue: 100
                }, {
                    show: true,
                    type: 'inside',
                    filterMode: 'none',
                    yAxisIndex: [0],
                    startValue: 0,
                    endValue: this.Sinit,
                }]
            })
            this.updateData();
        },
        Iinit: function(value) {
            console.log("Iinit, ", value)
            this.updateData();
        },
        T: function(value) {
            console.log("T, ", value)
            this.updateData();
        }
    },
    methods: {
        updateData() {
            this.initParams();
        },

        displayChart() {
            var lst_s = [] ;        //'Susceptible'
            var lst_e = [] ;        //'Exposed'
            var lst_i = [] ;        //'Infected'
            var lst_r = [] ;        //'Recovered'

            for ( var i=0; i<this.lst.length; i++ ) {
                lst_s.push([i,this.lst[i][0] ] );
                lst_e.push([i,this.lst[i][1] ] );
                lst_i.push([i,this.lst[i][2] ] );
                lst_r.push([i,this.lst[i][3] ] );
            }

            // console.log(lst_s);
            // console.log(lst_e);
            // console.log(lst_i);
            // console.log(lst_r);


            // 对chart数据进行更新
            var dom = document.getElementById("container");
            var myChart = echarts.init(dom);
            myChart.setOption({
                // ['S-易感人群', 'E-潜伏人群', 'I-发病人群', 'R-治愈人群']
                series: [
                    {
                        name: 'S-易感人群',
                        type: 'line',
                        showSymbol: false,
                        clip: true,
                        data: lst_s
                    },
                    {
                        name: 'E-潜伏人群',
                        type: 'line',
                        showSymbol: false,
                        clip: true,
                        data: lst_e
                    },
                    {
                        name: 'I-发病人群',
                        type: 'line',
                        showSymbol: false,
                        clip: true,
                        data: lst_i
                    },
                    {
                        name: 'R-治愈人群',
                        type: 'line',
                        showSymbol: false,
                        clip: true,
                        data: lst_r
                    }
                ],
                yAxis: {
                    name: 'y',
                    min: 0,
                    max: this.Sinit,
                    minorTick: {
                        show: true
                    }
                }
            })
        },

        // 计算函数
        // 求微分, 传入v[S, E, I, R], 
        seir_eq(v,t,alpha,beta,gamma,N) {
            // console.log("v:",v,",t:",t,",alpha:",alpha,",beta:",beta,",gamma:",gamma,",N:",N);
            var S = v[0];
            var E = v[1];
            var I = v[2];
            // var R = v[3];
            var ds = - beta * I / N * S             // dS/dt = -βI/N*S
            var de = beta * I / N * S - alpha * E   // dE/dt = βI/N*S-αE
            var di = alpha * E - gamma * I          // dI/dt = αE - γI
            var dr = gamma * I                      // dR/dt = γI 

            return [ds,de,di,dr];
        },

        // 用于计算
        calc(state,alpha,beta,gamma) {
            var t_max = 100 ;
            // var dt = 1 ;
            this.lst = []
            var N = this.Sinit + this.Einit + this.Iinit + this.Rinit
            // console.log( "init 0: ", state );

            for ( var i=0; i<t_max; i++ ) {

                var d =  this.seir_eq( state, i, alpha,beta,gamma, N )
                var Si = state[0]+d[0]
                var Ei = state[1]+d[1]
                var Ii = state[2]+d[2]
                var Ri = state[3]+d[3]
                // 查找并隔离感染者
                var dx = Ii * Number(this.T)
                Ii = Ii - dx
                Ri = Ri + dx // 加入免疫接种的人

                // 调整负值
                if ( Si < 0 ) {
                    Ei = Ei + Si; Si = 0;
                }
                if ( Ei < 0 ) {
                    Ii = Ii + Ei; Ei = 0;
                }
                if ( Ii < 0 ) {
                    Ri = Ri + Ii; Ii = 0;
                }

                state = [ Si, Ei, Ii, Ri ]
                // console.log( state );
                this.lst.push( state );
            }
        },

        avoidWarning(param) {
            console.log(param);
        },


        initPlot() {
            var dom = document.getElementById("container");
            var myChart = echarts.init(dom);
            //var app = {};
            var option = null;
            option = {
                animation: false,
                // grid: {
                //     top: 40,
                //     left: 50,
                //     right: 40,
                //     bottom: 50
                // },
                xAxis: {
                    axisLine: {
                        lineStyle: {
                            color: '#FFFFFF',
                            width: 2
                        }
                    },
                    name: 'x',
                    min: 0,
                    max: 100,
                    minorTick: {
                        show: true
                    },
                    splitLine: {
                        show: true,
                        lineStyle: {
                            color: '#5f7685'
                        }
                    },
                    minorSplitLine: {
                        show: true,
                        lineStyle: {
                            color: '#3c4c57'
                        }
                    }
                },
                yAxis: {
                    axisLine: {
                        lineStyle: {
                            color: '#FFFFFF',
                            width: 2
                        }
                    },
                    name: 'y',
                    min: 0,
                    max: this.Sinit,
                    minorTick: {
                        show: true
                    },
                    splitLine: {
                        show: true,
                        lineStyle: {
                            color: '#5f7685'
                        }
                    },
                    minorSplitLine: {
                        show: true,
                        lineStyle: {
                            color: '#3c4c57'
                        }
                    }
                },
                dataZoom: [{
                    show: true,
                    type: 'inside',
                    filterMode: 'none',
                    xAxisIndex: [0],
                    startValue: 0,
                    endValue: 100
                }, {
                    show: true,
                    type: 'inside',
                    filterMode: 'none',
                    yAxisIndex: [0],
                    startValue: 0,
                    endValue: this.Sinit,
                }],
                zoomOnMouseWheel: false,
                moveOnMouseMove: false,
                legend: {
                    textStyle: {
                        color: '#FFFFFF',
                        fontWeight: 'bolder'
                    },
                    icon: 'pin',
                    data: ['S-易感人群', 'E-潜伏人群', 'I-发病人群', 'R-治愈人群']
                },
                grid: {
                    show: true,
                    backgroundColor: '#303843'
                },
                color: ['#646299', '#afcaed', '#a3d28a', '#ed97be'],
                series: [],
                tooltip: {
                    show: true,
                    trigger: 'axis'
                }
            };
            if (option && typeof option == "object"){
                myChart.setOption(option, true);
            }
        },

        // 初始化参数
        initParams() {
            // 第一次进行初始化, 或每一次重绘使用
            var Sinit = Number(this.Sinit);
            var Einit = 0;
            var Iinit = Number(this.Iinit);
            var Rinit = 0;
            
            var R0 = Number(this.R0);
            var lp = Number(this.lp);
            var ip = Number(this.ip);
            var D = R0*ip; this.D = D;
            var T = Number(this.T);

            var alpha = 1/lp; this.alpha = alpha.toFixed(4);
            var gamma = 1/ip; this.gamma = gamma.toFixed(4);
            var beta = R0*gamma; this.beta = beta.toFixed(4);

            console.log("R0: " + R0 );
            console.log("beta: " + beta );
            console.log("lp: " + lp );
            console.log("ip: " + ip );
            console.log("T: " + T );

            var state = [Sinit,Einit,Iinit,Rinit];
            this.calc(state, alpha, beta, gamma);
            this.displayChart();
        }
    },
    mounted() {
        this.initPlot();
        this.initParams();
        

    }
}
</script>

<style scoped>
    .el-card__body {
        padding: 20px;
    }
    .el-card__header {
        box-sizing: border-box;
    }

    .ol-wrapper {
        color: #cfcfcf;
    }

    h3 {
        color: #FFFFFF;
    }

    .map-wrapper {
        height: 100vh;
        width: 100%;
    }
    .chart-wrapper {
        width: 100%;
        height: 50vh;
    }

    .text-wrapper {
        text-align: left;
        margin-left: 10px;
        margin-right: 10px;
    }

    p {
        text-indent: 32px;
    }

    p span {
        text-indent: 0;
    }
</style>