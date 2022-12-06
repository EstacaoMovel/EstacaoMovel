package pt.isec.gps.tp.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class DataHora {


    private String date;

    public DataHora() {
        setDate("");
        //this.date = "9/12/2022";
    }


    public void setDate(String dataAnterior) {

        int diaI, mesI, anoI;

        if (dataAnterior.equals("")) {
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
            diaI = Integer.parseInt(diaS);
            mesI = Integer.parseInt(mesS);
            anoI = Integer.parseInt(anoS);
        }
        else {
            String[] tokens = dataAnterior.split("/");
            diaI = Integer.parseInt(tokens[0]);
            mesI = Integer.parseInt(tokens[1]);
            anoI = Integer.parseInt(tokens[2]);

            diaI++;
        }



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
        String data = dia.concat("/").concat(mes).concat("/").concat(ano);

        // ATUALIZAR A dataConsulta;
        //this.date = data;
        this.date = data;

    }

    public String getDate(int aux) {
        switch (aux) {
            case 0 -> {
                return date;
            }
            case 1 -> {
                setDate(date);
                return date;
            }
        }
        return "";
    }
}
