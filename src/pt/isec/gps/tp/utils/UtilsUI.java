package pt.isec.gps.tp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilsUI {
    public static final double appWidthSize = 337.50;
    public static final double appHeightSize = 600;

    Date dataHoraAtual = new Date();
    String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
}
