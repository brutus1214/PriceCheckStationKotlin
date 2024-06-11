package com.example.pricecheckstationkotlin.util

val upca12: String =
    "\u0010CT~~CD,~CC^~CT~\n" +
            "^XA~TA000~JSN^LT0^MNW^MTD^PON^PMN^LH0,0^JMA^PR4,4~SD10^JUS^LRN^CI0^XZ\n" +
            "^XA\n" +
            "^MMT\n" +
            "^PW500\n" +
            "^LL0200\n" +
            "^LS0\n" +
            "^FX Description 1, 2, 3" +
            "^FT60,50^A0N,27,26^FH\\^FD%DESC1%^FS\n" +
            "^FT250,50^A0N,27,26^FH\\^FD%DESC2%^FS\n" +
            "^FT350,50^A0N,27,26^FH\\^FD%DESC3%^FS\n" +
            "^FX Item Description" +
            "^FT60,100^A0N,60,40^FH\\^FD%DESC%^FS\n" +

            "^FX UPCA Barcode" +
            "^FT60,160^BY1.5,3.0,4^BUN,40,Y,N,Y^FH\\^FD%BARCODE%^FS\n" +

            "^FX Today Date sign" +
            "^FT60,200^A0N,20,20^FH\\^FD%DATE%^FS\n" +

            "^FX $ sign" +
            "^FT260,160^A0N,60,20^FH\\^FD%DOLLAR%^FS\n" +

            "^FX Price" +
            "^FT280,190^A0N,100,60^FH\\^FD%PRICE%^FS\n" +


            //"^FO171,119^GB259,0,2^FS\n" + // Draw line
            "^PQ1,0,1,Y^XZ\n"

val ean13: String =
    "\u0010CT~~CD,~CC^~CT~\n" +
            "^XA~TA000~JSN^LT0^MNW^MTD^PON^PMN^LH0,0^JMA^PR4,4~SD10^JUS^LRN^CI0^XZ\n" +
            "^XA\n" +
            "^MMT\n" +
            "^PW500\n" +
            "^LL0200\n" +
            "^LS0\n" +
            "^FX Description 1, 2, 3" +
            "^FT60,50^A0N,27,26^FH\\^FD%DESC1%^FS\n" +
            "^FT250,50^A0N,27,26^FH\\^FD%DESC2%^FS\n" +
            "^FT350,50^A0N,27,26^FH\\^FD%DESC3%^FS\n" +
            "^FX Item Description" +
            "^FT60,100^A0N,60,40^FH\\^FD%DESC%^FS\n" +

            "^FX UPCA Barcode" +
            "^FT60,160^BY1.5,3.0,4^BEN,40,Y,N,Y^FH\\^FD%BARCODE%^FS\n" +

            "^FX Today Date sign" +
            "^FT60,200^A0N,20,20^FH\\^FD%DATE%^FS\n" +

            "^FX $ sign" +
            "^FT260,160^A0N,60,20^FH\\^FD%DOLLAR%^FS\n" +

            "^FX Price" +
            "^FT280,190^A0N,100,60^FH\\^FD%PRICE%^FS\n" +


//"^FO171,119^GB259,0,2^FS\n" + // Draw line
            "^PQ1,0,1,Y^XZ\n"

val nobarcode: String =
    "\u0010CT~~CD,~CC^~CT~\n" +
            "^XA~TA000~JSN^LT0^MNW^MTD^PON^PMN^LH0,0^JMA^PR4,4~SD10^JUS^LRN^CI0^XZ\n" +
            "^XA\n" +
            "^MMT\n" +
            "^PW500\n" +
            "^LL0200\n" +
            "^LS0\n" +
            "^FX Description 1, 2, 3" +
            "^FT60,50^A0N,27,26^FH\\^FD%DESC1%^FS\n" +
            "^FT250,50^A0N,27,26^FH\\^FD%DESC2%^FS\n" +
            "^FT350,50^A0N,27,26^FH\\^FD%DESC3%^FS\n" +
            "^FX Item Description" +
            "^FT60,100^A0N,60,40^FH\\^FD%DESC%^FS\n" +

            "^FX UPCA Barcode" +
            "^FT60,160^BY1.5,3.0,4^BUN,40,Y,N,Y^FH\\^FD%BARCODE%^FS\n" +

            "^FX Today Date sign" +
            "^FT60,200^A0N,20,20^FH\\^FD%DATE%^FS\n" +

            "^FX $ sign" +
            "^FT260,160^A0N,60,20^FH\\^FD%DOLLAR%^FS\n" +

            "^FX Price" +
            "^FT280,190^A0N,100,60^FH\\^FD%PRICE%^FS\n" +


            //"^FO171,119^GB259,0,2^FS\n" + // Draw line
            "^PQ1,0,1,Y^XZ\n"