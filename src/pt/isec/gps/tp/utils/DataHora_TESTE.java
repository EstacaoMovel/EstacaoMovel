package pt.isec.gps.tp.utils;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

public class DataHora_TESTE {

    Date dataHoraAtual = new Date();
    private String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);

    public String getData() {
        return data;
    }


    private String dataConsulta;


    public void setData(String intervalo) {

        // OBTER A DATA DO SISTEMA
        LocalDate date = LocalDate.now();

        // SEPARAR O DIA, O M�S E O ANO DA DATA
        DateTimeFormatter diaSyst = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter mesSyst = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter anoSyst = DateTimeFormatter.ofPattern("yyyy");

        // CONVERTER OS DADOS DA DATA PARA STRING
        String diaS = date.format(diaSyst);
        String mesS = date.format(mesSyst);
        String anoS = date.format(anoSyst);

        // CONVERTER OS DADOS DA DATA DE STRING PARA INT
        int diaI = Integer.parseInt(diaS);
        int mesI = Integer.parseInt(mesS);
        int anoI = Integer.parseInt(anoS);

		/*
		System.out.println(diaS + "-" + mesS + "-" + anoS);
		System.out.println(diaI + "-" + mesI + "-" + anoI);

		diaI = 29;
		mesI = 2;
		anoI = 2023;
		*/
		/*
		diaI = 31;
		mesI = 12;
		anoI = 2022;
		*/

        if (intervalo.equals("ANUAL")) {
            anoI++;
        }
        else if (intervalo.equals("SEMESTRAL")) {
            if (mesI > 6) {
                mesI = mesI - 6;
                anoI++;
            }
            else {
                mesI+=6;
            }
        }
        else if (intervalo.equals("MENSAL")) {
            if (mesI == 12) {
                mesI = 1;
            }
            else {
                mesI++;
            }
        }


        boolean encontrou = false;

        while (!encontrou) {

            // VERIFICAR SE O M�S TEM 30 OU 31 DIAS
            int[] trinta = {4, 6, 9, 11};

            for (int i = 0; i < trinta.length; i++) {
                if (mesI == trinta[i] && diaI > 30) {
                    mesI++;
                    diaI = 1;
                }
            }

            // VERIFICAR SE O M�S � FEVEREIRO
            if ( (mesI == 2 && diaI > 29) || (mesI == 2 && diaI == 29 && (anoI%4) != 0) ) {
                mesI = 3;
                diaI = 1;
            }

            LocalDate someDate = LocalDate.of(anoI, mesI, diaI); // 2nd-Jan-2021

            DayOfWeek day = DayOfWeek.of(someDate.get(ChronoField.DAY_OF_WEEK));



            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                if (diaI < 31)
                    diaI++;
                else {
                    diaI = 1;
                    if (mesI < 12) {
                        mesI++;
                    }
                    else {
                        mesI = 1;
                        anoI++;
                    }

                }
                encontrou = false;
            }
            else
                encontrou = true;
        }

        // CONVERTER OS DADOS DE INT PARA STRING
        String dia = Integer.toString(diaI);
        String mes = Integer.toString(mesI);
        String ano = Integer.toString(anoI);

        // CONCATENAR AS 3 STRINGS PARA UMA STRING �NICA
        String data = dia.concat("-").concat(mes).concat("-").concat(ano);

        // ATUALIZAR A dataConsulta;
        this.dataConsulta = data;

    }
}
