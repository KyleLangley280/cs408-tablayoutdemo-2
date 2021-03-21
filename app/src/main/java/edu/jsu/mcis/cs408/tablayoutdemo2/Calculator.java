package edu.jsu.mcis.cs408.tablayoutdemo2;



import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Calculator {

    private FragmentOne parent;
    private BigDecimal lValue, rValue;
    private StringBuilder inputBuffer= new StringBuilder();
    private String displayBuffer = new String();
    private String operator;
    MathContext m = new MathContext(5, RoundingMode.HALF_UP);
    private Boolean equals = true;
    private String holder;
    private double working;
    private Boolean endCheck;

    public Calculator(FragmentOne parent){
        this.parent = parent;
    }

    public void process(String button) {
        if (button.equals(parent.getResources().getString(R.string.btn0))) {
            inputBuffer.append(parent.getResources().getString(R.string.btn0));
            displayBuffer += parent.getResources().getString(R.string.btn0);
        }
        if (button.equals(parent.getResources().getString(R.string.btn1))) {
            inputBuffer.append(parent.getResources().getString(R.string.btn1));
            displayBuffer += (parent.getResources().getString(R.string.btn1));
        }
        if (button.equals(parent.getResources().getString(R.string.btn2))) {
            inputBuffer.append(parent.getResources().getString(R.string.btn2));
            displayBuffer += (parent.getResources().getString(R.string.btn2));
        }
        if (button.equals(parent.getResources().getString(R.string.btn3))) {
            inputBuffer.append(parent.getResources().getString(R.string.btn3));
            displayBuffer += (parent.getResources().getString(R.string.btn3));
        }
        if (button.equals(parent.getResources().getString(R.string.btn4))) {
            inputBuffer.append(parent.getResources().getString(R.string.btn4));
            displayBuffer += (parent.getResources().getString(R.string.btn4));
        }
        if (button.equals(parent.getResources().getString(R.string.btn5))) {
            inputBuffer.append(parent.getResources().getString(R.string.btn5));
            displayBuffer += (parent.getResources().getString(R.string.btn5));
        }
        if (button.equals(parent.getResources().getString(R.string.btn6))) {
            inputBuffer.append(parent.getResources().getString(R.string.btn6));
            displayBuffer += (parent.getResources().getString(R.string.btn6));
        }
        if (button.equals(parent.getResources().getString(R.string.btn7))) {
            inputBuffer.append(parent.getResources().getString(R.string.btn7));
            displayBuffer += (parent.getResources().getString(R.string.btn7));
        }
        if (button.equals(parent.getResources().getString(R.string.btn8))) {
            inputBuffer.append(parent.getResources().getString(R.string.btn8));
            displayBuffer += (parent.getResources().getString(R.string.btn8));
        }
        if (button.equals(parent.getResources().getString(R.string.btn9))) {
            inputBuffer.append(parent.getResources().getString(R.string.btn9));
            displayBuffer += (parent.getResources().getString(R.string.btn9));
        }

        //**************POSITIVE NEGATIVE*************************


        if (button.equals(parent.getResources().getString(R.string.btnPosNeg))) {
            //operator, first value, and no second value, something on display
            if (operator != null && lValue != null && inputBuffer == null) {
                displayBuffer = "ERROR NEGATIVE A SIGN, PLEASE CLEAR";
            }
            //operator,1st value, 2nd value present
            if (operator != null && lValue != null && inputBuffer != null) {
                BigDecimal working2 = BigDecimal.valueOf(Double.parseDouble(inputBuffer.toString()));
                working2 = working2.negate();
                displayBuffer = displayBuffer.replaceAll(inputBuffer.toString(), working2.toString());
                inputBuffer = new StringBuilder();
                inputBuffer.append(working2);
            }
            //Occurs when there is no number set, something inside of the buffer, and no operator ("12")
            if (displayBuffer != null && operator == null && lValue == null) {
                BigDecimal working2 = BigDecimal.valueOf(Double.parseDouble(inputBuffer.toString()));
                working2 = working2.negate();
                displayBuffer = working2.toString();
                inputBuffer = new StringBuilder();
                inputBuffer.append(working2);
            }
            if(displayBuffer.isEmpty() && operator == null && lValue!=null){
                BigDecimal working2 = lValue;
                working2 = working2.negate();
                displayBuffer = displayBuffer.replaceAll(lValue.toString(), working2.toString());
                lValue = working2;
            }
        }

        //***********DECIMAL**********************

        if (button.equals(parent.getResources().getString(R.string.btnDecimal))) {
            if (inputBuffer.indexOf(".") == -1) {
                inputBuffer.append(parent.getResources().getString(R.string.btnDecimal));
                displayBuffer = displayBuffer + ".";
            } else {

            }
            //this.displayBuffer = this.inputBuffer.toString();
        }

        //*************************ADDITION****************************

        if (button.equals(parent.getResources().getString(R.string.btnPlus))) {
            if (!displayBuffer.isEmpty()) {
                char endChar = displayBuffer.charAt(displayBuffer.length() - 1);
                endCheck = Character.isDigit(endChar);
            } else {
                endCheck = true;
            }

            if (displayBuffer.isEmpty() || !endCheck) {

            } else if (!displayBuffer.isEmpty() && operator == null) {
                if (inputBuffer.length() != 0 || equals == true) {
                    lValue = (BigDecimal.valueOf(Double.parseDouble(inputBuffer.toString()))).round(m);
                    inputBuffer = new StringBuilder();
                    operator = parent.getResources().getString(R.string.btnPlus);
                    displayBuffer += parent.getResources().getString(R.string.btnPlus);
                    equals = false;
                }
            } else if (!displayBuffer.isEmpty() && operator != null) {
                rValue = (BigDecimal.valueOf(Double.parseDouble(inputBuffer.toString()))).round(m);
                lValue = processPrevious().round(m);
                displayBuffer = lValue.toString();
                rValue = null;
                inputBuffer = new StringBuilder();
                displayBuffer += parent.getResources().getString(R.string.btnPlus);
                operator = parent.getResources().getString(R.string.btnPlus);

            }
        }

        //*************************SUBTRACTION****************************
        if (button.equals(parent.getResources().getString(R.string.btnMinus))) {
            if (!displayBuffer.isEmpty()) {
                char endChar = displayBuffer.charAt(displayBuffer.length() - 1);
                endCheck = Character.isDigit(endChar);
            } else {
                endCheck = true;
            }
            if (displayBuffer.isEmpty() || !endCheck) {

            } else if (!displayBuffer.isEmpty() && operator == null) {
                if (inputBuffer.length() != 0 || equals == true) {
                    lValue = (BigDecimal.valueOf(Double.parseDouble(inputBuffer.toString()))).round(m);
                    inputBuffer = new StringBuilder();
                    operator = parent.getResources().getString(R.string.btnMinus);
                    displayBuffer += parent.getResources().getString(R.string.btnMinus);
                    equals = false;
                }
            } else if (!displayBuffer.isEmpty() && operator != null) {
                rValue = (BigDecimal.valueOf(Double.parseDouble(inputBuffer.toString()))).round(m);
                lValue = processPrevious().round(m);
                displayBuffer = lValue.toString();
                rValue = null;
                inputBuffer = new StringBuilder();
                displayBuffer += parent.getResources().getString(R.string.btnMinus);
                operator = parent.getResources().getString(R.string.btnMinus);

            }
        }

        //********************DIVISION******************

        if (button.equals(parent.getResources().getString(R.string.btnDivis))) {
            if (!displayBuffer.isEmpty()) {
                char endChar = displayBuffer.charAt(displayBuffer.length() - 1);
                endCheck = Character.isDigit(endChar);
            } else {
                endCheck = true;
            }
            if (displayBuffer.isEmpty() || !endCheck) {

            } else if (!displayBuffer.isEmpty() && operator == null) {
                if (inputBuffer.length() != 0 || equals == true) {
                    lValue = (BigDecimal.valueOf(Double.parseDouble(inputBuffer.toString()))).round(m);
                    inputBuffer = new StringBuilder();
                    operator = parent.getResources().getString(R.string.btnDivis);
                    displayBuffer += parent.getResources().getString(R.string.btnDivis);
                    equals = false;
                }
            } else if (!displayBuffer.isEmpty() && operator != null) {
                rValue = (BigDecimal.valueOf(Double.parseDouble(inputBuffer.toString()))).round(m);
                lValue = processPrevious().round(m);
                displayBuffer = lValue.toString();
                rValue = null;
                inputBuffer = new StringBuilder();
                displayBuffer += parent.getResources().getString(R.string.btnDivis);
                operator = parent.getResources().getString(R.string.btnDivis);

            }
        }

        //****************MULTIPLICATION***************************

        if (button.equals(parent.getResources().getString(R.string.btnMulti))) {
            if (!displayBuffer.isEmpty()) {
                char endChar = displayBuffer.charAt(displayBuffer.length() - 1);
                endCheck = Character.isDigit(endChar);
            } else {
                endCheck = true;
            }
            if (displayBuffer.isEmpty() || !endCheck) {

            } else if (!displayBuffer.isEmpty() && operator == null) {
                if (inputBuffer.length() != 0 || equals == true) {
                    lValue = (BigDecimal.valueOf(Double.parseDouble(inputBuffer.toString()))).round(m);
                    inputBuffer = new StringBuilder();
                    operator = parent.getResources().getString(R.string.btnMulti);
                    displayBuffer += parent.getResources().getString(R.string.btnMulti);
                    equals = false;
                }
            } else if (!displayBuffer.isEmpty() && operator != null) {
                rValue = (BigDecimal.valueOf(Double.parseDouble(inputBuffer.toString()))).round(m);
                lValue = processPrevious().round(m);
                displayBuffer = lValue.toString();
                rValue = null;
                inputBuffer = new StringBuilder();
                displayBuffer += parent.getResources().getString(R.string.btnMulti);
                operator = parent.getResources().getString(R.string.btnMulti);

            }
        }
        //*********************MODULO********************************

        if (button.equals(parent.getResources().getString(R.string.btnMod))) {
            if (!displayBuffer.isEmpty()) {
                char endChar = displayBuffer.charAt(displayBuffer.length() - 1);
                endCheck = Character.isDigit(endChar);
            } else {
                endCheck = true;
            }
            if (displayBuffer.isEmpty() || !endCheck) {

            } else if (!displayBuffer.isEmpty() && operator == null) {
                if (inputBuffer.length() != 0 || equals == true) {
                    lValue = (BigDecimal.valueOf(Double.parseDouble(inputBuffer.toString()))).round(m);
                    inputBuffer = new StringBuilder();
                    operator = parent.getResources().getString(R.string.btnMod);
                    displayBuffer += parent.getResources().getString(R.string.btnMod);
                    equals = false;
                }

            } else if (!displayBuffer.isEmpty() && operator != null ) {
                rValue = (BigDecimal.valueOf(Double.parseDouble(inputBuffer.toString()))).round(m);
                lValue = processPrevious().round(m);
                displayBuffer = lValue.toString();
                rValue = null;
                inputBuffer = new StringBuilder();
                displayBuffer += parent.getResources().getString(R.string.btnMod);
                operator = parent.getResources().getString(R.string.btnMod);

            }
        }

        //*****************SQUARE ROOT******************************

        if (button.equals(parent.getResources().getString(R.string.btnSqrt))) {
            //operator, first value, and no second value, something on display
            if (operator != null && lValue != null && inputBuffer == null) {
                displayBuffer = "ERROR CAN'T SQUARE A SIGN, PLEASE CLEAR";
            }
            //operator,1st value, 2nd value present
            if (operator != null && lValue != null && inputBuffer != null) {
                working = (Double.parseDouble(inputBuffer.toString()));
                working = Math.pow(working,0.5);
                //BigDecimal working2 = BigDecimal.valueOf(Double.parseDouble(inputBuffer.toString()));
                //working2.pow(1 / 2);
                displayBuffer = displayBuffer.replaceAll(inputBuffer.toString(), String.valueOf(working));
                inputBuffer = new StringBuilder();
                inputBuffer.append(working);
            }
            //Occurs when there is no number set, something inside of the buffer, and no operator ("12")
            if (displayBuffer != null && operator == null && lValue == null) {
                working = (Double.parseDouble(inputBuffer.toString()));
                working = Math.pow(working,0.5);
                //BigDecimal working2 = BigDecimal.valueOf(Double.parseDouble(inputBuffer.toString()));
                //working2.pow(1/2);
                displayBuffer = String.valueOf(working);
                inputBuffer = new StringBuilder();
                inputBuffer.append(working);
            }
        }

        //**************CLEAR***************************************

        if (button.equals(parent.getResources().getString(R.string.btnClear))) {
            lValue = null;
            rValue = null;
            inputBuffer = new StringBuilder();
            displayBuffer = new String();
            operator = null;
        }

        //**********************EQUAL*****************************

        if (button.equals(parent.getResources().getString(R.string.btnEqual))) {
            char endChar = displayBuffer.charAt(displayBuffer.length() - 1);
            Boolean endCheck = Character.isDigit(endChar);
            if (displayBuffer.isEmpty()) {

            } else if (!displayBuffer.isEmpty() && operator == null) {

            } else if (!displayBuffer.isEmpty() && operator != null && !endCheck) {
                displayBuffer = "ERROR WRONG FUNCTION, PLEASE CLEAR";
            } else if (!displayBuffer.isEmpty() && operator != null && endCheck) {
                rValue = (BigDecimal.valueOf(Double.parseDouble(inputBuffer.toString()))).round(m);
                lValue = processPrevious().round(m);
                displayBuffer = lValue.toString();
                operator = null;
                rValue = null;
                inputBuffer = new StringBuilder();
                inputBuffer.append(lValue);
                equals = true;
            }
        }
    }


    public String getDisplay(){
        return this.displayBuffer;
    }
    public BigDecimal processPrevious(){
        if(operator.equals(parent.getResources().getString(R.string.btnPlus))){
            return (lValue.add(rValue,m));
        }
        else if(operator.equals(parent.getResources().getString(R.string.btnMinus))){
            return (lValue.subtract(rValue,m));
        }
        else if(operator.equals(parent.getResources().getString(R.string.btnMulti))){
            return (lValue.multiply(rValue,m));
        }
        else if(operator.equals(parent.getResources().getString(R.string.btnDivis))){
            return lValue.divide(rValue,m);
        }
        else if(operator.equals(parent.getResources().getString(R.string.btnMod))){
            return (lValue.remainder(rValue,m));
        }
        else{
            return null;
        }
    }
}
