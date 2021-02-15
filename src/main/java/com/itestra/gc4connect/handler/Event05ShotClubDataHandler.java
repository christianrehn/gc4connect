package com.itestra.gc4connect.handler;

import com.itestra.gc4connect.data.GC4ShotClubData;
import com.itestra.gc4connect.message.GC4Message;
import org.apache.commons.lang3.Validate;

/**
 * Club data
 */
public class Event05ShotClubDataHandler extends GC4MessageHandler {

    public static final String UNKNOWN_002_TO_005 = "38000000";

    @Override
    public String getDefaultHexMessageString() {
        return null;
    }

    public GC4ShotClubData handleHexMessageString(String hexMessageString) {
        Validate.notEmpty(hexMessageString);
        Validate.isTrue(hexMessageString.length() == 2 * MESSAGE_LENGTH_BYTES_RESPONSE05, "" + hexMessageString.length());

        super.handleHexMessageString(hexMessageString);

        System.out.println(String.format("SHOT CLUB DATA EVENT"));
        GC4ShotClubData gc4ShotClubData = new GC4ShotClubData();

        validateHexString(0, OPERATION_RESPONSE05, hexMessageString);
        validateHexString(1, DIRECTION_001_GC4_EVENT, hexMessageString);

        // unknown int 1, always 0x38000000
        validateHexString(2, UNKNOWN_002_TO_005, hexMessageString);

        // unkown int 2
        {
            String unknownAsHexString = hexSubString(6, 4, hexMessageString);
            gc4ShotClubData.unknownInt2 = GC4Message.lBytesToInt(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 6 to 9 (0x%s) int value=%d", unknownAsHexString, gc4ShotClubData.unknownInt2));
        }

        // shotNumber
        {
            String shotNumberAsHexString = hexSubString(10, 4, hexMessageString);
            gc4ShotClubData.shotNumber = GC4Message.lBytesToInt(GC4Message.hexStringToByteArray(shotNumberAsHexString));
            System.out.println(String.format("position 10 to 13 (0x%s) int shotNumber=%d", shotNumberAsHexString, gc4ShotClubData.shotNumber));
        }

        // club speed
        {
            String clubSpeedAsHexString = hexSubString(14, 4, hexMessageString);
            gc4ShotClubData.clubSpeed = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(clubSpeedAsHexString));
            System.out.println(String.format("position 14 to 17 (0x%s) clubSpeed=%f MPH", clubSpeedAsHexString, gc4ShotClubData.clubSpeed));
        }

        // unkown float 2
        {
            String unknownAsHexString = hexSubString(18, 4, hexMessageString);
            gc4ShotClubData.unknownFloat2 = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 18 to 21 (0x%s) unknownFloat2=%f", unknownAsHexString, gc4ShotClubData.unknownFloat2));
        }

        // angle of attack
        {
            String angleOfAttackAsHexString = hexSubString(22, 4, hexMessageString);
            gc4ShotClubData.angleOfAttack = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(angleOfAttackAsHexString));
            System.out.println(String.format("position 22 to 25 (0x%s) angleOfAttack=%f° (<0 -> down)", angleOfAttackAsHexString, gc4ShotClubData.angleOfAttack));
        }

        // club path
        {
            String clubPathAsHexString = hexSubString(26, 4, hexMessageString);
            gc4ShotClubData.clubPath = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(clubPathAsHexString));
            System.out.println(String.format("position 26 to 29 (0x%s) clubPath=%f° (<0 -> out-to-in", clubPathAsHexString, gc4ShotClubData.clubPath));
        }

        // face to target
        {
            String faceToTargetAsHexString = hexSubString(30, 4, hexMessageString);
            gc4ShotClubData.faceToTarget = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(faceToTargetAsHexString));
            System.out.println(String.format("position 30 to 33 (0x%s) faceToTarget=%f° (<0 -> open)", faceToTargetAsHexString, gc4ShotClubData.faceToTarget));
        }

        // lie
        {
            String lieAsHexString = hexSubString(34, 4, hexMessageString);
            gc4ShotClubData.lie = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(lieAsHexString));
            System.out.println(String.format("position 34 to 37 (0x%s) lie=%f° (<0 -> toe down)", lieAsHexString, gc4ShotClubData.lie));
        }

        // loft
        {
            String loftAsHexString = hexSubString(38, 4, hexMessageString);
            gc4ShotClubData.loft = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(loftAsHexString));
            System.out.println(String.format("position 38 to 41 (0x%s) loft=%f°", loftAsHexString, gc4ShotClubData.loft));
        }

        // face impact lateral
        {
            String faceImpactLateralAsHexString = hexSubString(42, 4, hexMessageString);
            gc4ShotClubData.faceImpactLateral = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(faceImpactLateralAsHexString));
            System.out.println(String.format("position 42 to 45 (0x%s) faceImpactLateral=%f mm (>0 -> heel, <0 -> toe", faceImpactLateralAsHexString, gc4ShotClubData.faceImpactLateral));
        }

        // face impact vertical
        {
            String faceImpactVerticalAsHexString = hexSubString(46, 4, hexMessageString);
            gc4ShotClubData.faceImpactVertical = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(faceImpactVerticalAsHexString));
            System.out.println(String.format("position 46 to 49 (0x%s) faceImpactVertical=%f mm (>0 -> high, <0 -> low", faceImpactVerticalAsHexString, gc4ShotClubData.faceImpactVertical));
        }

        // unkown float 10
        {
            String unknownAsHexString = hexSubString(50, 4, hexMessageString);
            gc4ShotClubData.unknownFloat10 = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 50 to 53 (0x%s) unknownFloat10=%f", unknownAsHexString, gc4ShotClubData.unknownFloat10));
        }
        // unkown float 11
        {
            String unknownAsHexString = hexSubString(54, 4, hexMessageString);
            gc4ShotClubData.unknownFloat11 = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(unknownAsHexString));
            System.out.println(String.format("position 54 to 57 (0x%s) unknownFloat11=%f", unknownAsHexString, gc4ShotClubData.unknownFloat11));
        }
        // closure rate
        {
            String closureRateAsHexString = hexSubString(58, 4, hexMessageString);
            gc4ShotClubData.closureRate = GC4Message.lBytesToFloat(GC4Message.hexStringToByteArray(closureRateAsHexString));
            System.out.println(String.format("position 58 to 61 (0x%s) closureRate=%f dps", closureRateAsHexString, gc4ShotClubData.closureRate));
        }

        return gc4ShotClubData;
    }

}
