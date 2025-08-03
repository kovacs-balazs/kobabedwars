package me.koba1.kobabedwars.utils;

import me.koba1.kobabedwars.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {

    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");
    private static final Pattern GRADIENT_PATTERN = Pattern.compile("<gradient:(#[A-Fa-f0-9]{6}):(#[A-Fa-f0-9]{6})>([^<]*)</gradient>");

    public static String formatToString(String text) {
        if (text == null) {
            return "";
        }
        text = ColorUtils.processGradients(text);
        text = ColorUtils.processHexColors(text);
        text = text.replace("&", "§");
        return text;
    }

    public static String formatToString(Player p, String text) {
        if (text == null) {
            return "";
        }
        if (Main.getInstance().getHookManager().isPapi())
            text = PlaceholderAPI.setPlaceholders(p, text);
        return formatToString(text);
    }

    public static Component format(Player p, String text) {
        if (text == null) {
            return Component.empty();
        }

        if (Main.getInstance().getHookManager().isPapi())
            text = PlaceholderAPI.setPlaceholders(p, text);
        return format(text);
    }

    public static Component format(String text) {
        if (text == null) {
            return Component.empty();
        }
        return ColorUtils.processTextAsComponent(text);
    }

    private static String processGradients(String text) {
        Matcher matcher = GRADIENT_PATTERN.matcher(text);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String startColor = matcher.group(1);
            String endColor = matcher.group(2);
            String content = matcher.group(3);
            String gradientText = ColorUtils.createGradientString(content, startColor, endColor);
            matcher.appendReplacement(result, Matcher.quoteReplacement(gradientText));
        }
        matcher.appendTail(result);
        return result.toString();
    }

    private static String processHexColors(String text) {
        Matcher matcher = HEX_PATTERN.matcher(text);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String hexCode = matcher.group(1);
            String replacement = ColorUtils.convertHexToLegacy(hexCode);
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);
        return result.toString();
    }

    private static Component processTextAsComponent(String text) {
        Matcher gradientMatcher = GRADIENT_PATTERN.matcher(text);
        if (!gradientMatcher.find()) {
            return LegacyComponentSerializer.legacySection().deserialize(ColorUtils.formatToString(text));
        }
        Component result = Component.empty();
        int lastEnd = 0;
        gradientMatcher.reset();
        while (gradientMatcher.find()) {
            if (gradientMatcher.start() > lastEnd) {
                String beforeText = text.substring(lastEnd, gradientMatcher.start());
                result = result.append(LegacyComponentSerializer.legacySection().deserialize(ColorUtils.formatToString(beforeText)));
            }
            String startColor = gradientMatcher.group(1);
            String endColor = gradientMatcher.group(2);
            String content = gradientMatcher.group(3);
            Component gradientComponent = ColorUtils.createGradientComponent(content, startColor, endColor);
            result = result.append(gradientComponent);
            lastEnd = gradientMatcher.end();
        }
        if (lastEnd < text.length()) {
            String afterText = text.substring(lastEnd);
            result = result.append(LegacyComponentSerializer.legacySection().deserialize(ColorUtils.formatToString(afterText)));
        }
        return result;
    }

    private static String createGradientString(String text, String startHex, String endHex) {
        if (text.isEmpty()) {
            return "";
        }
        FormatCodes formatCodes = ColorUtils.extractFormatCodes(text);
        String cleanText = ColorUtils.removeAllFormatCodes(text);
        if (cleanText.isEmpty()) {
            return "";
        }
        Color startColor = Color.decode(startHex);
        Color endColor = Color.decode(endHex);
        StringBuilder result = new StringBuilder();
        int textLength = cleanText.length();
        for (int i = 0; i < textLength; ++i) {
            char c = cleanText.charAt(i);
            if (c == ' ') {
                result.append(c);
                continue;
            }
            double ratio = textLength == 1 ? 0.0 : (double) i / (double) (textLength - 1);
            int red = ColorUtils.interpolateColor(startColor.getRed(), endColor.getRed(), ratio);
            int green = ColorUtils.interpolateColor(startColor.getGreen(), endColor.getGreen(), ratio);
            int blue = ColorUtils.interpolateColor(startColor.getBlue(), endColor.getBlue(), ratio);
            String hexColor = String.format("%02x%02x%02x", red, green, blue);
            result.append(ColorUtils.convertHexToLegacy(hexColor));
            result.append(formatCodes.toLegacyString());
            result.append(c);
        }
        return result.toString();
    }

    private static Component createGradientComponent(String text, String startHex, String endHex) {
        if (text.isEmpty()) {
            return Component.empty();
        }
        FormatCodes formatCodes = ColorUtils.extractFormatCodes(text);
        String cleanText = ColorUtils.removeAllFormatCodes(text);
        if (cleanText.isEmpty()) {
            return Component.empty();
        }
        Color startColor = Color.decode(startHex);
        Color endColor = Color.decode(endHex);
        Component result = Component.empty();
        int textLength = cleanText.length();
        for (int i = 0; i < textLength; ++i) {
            char c = cleanText.charAt(i);
            double ratio = textLength == 1 ? 0.0 : (double) i / (double) (textLength - 1);
            int red = ColorUtils.interpolateColor(startColor.getRed(), endColor.getRed(), ratio);
            int green = ColorUtils.interpolateColor(startColor.getGreen(), endColor.getGreen(), ratio);
            int blue = ColorUtils.interpolateColor(startColor.getBlue(), endColor.getBlue(), ratio);
            TextColor color = TextColor.color(red, green, blue);
            Component charComponent = Component.text(c).color(color);
            charComponent = ColorUtils.applyFormatCodes(charComponent, formatCodes);
            result = result.append(charComponent);
        }
        return result;
    }

    private static FormatCodes extractFormatCodes(String text) {
        FormatCodes codes = new FormatCodes();
        codes.bold = text.contains("&l");
        codes.italic = text.contains("&o");
        codes.underlined = text.contains("&n");
        codes.strikethrough = text.contains("&m");
        codes.obfuscated = text.contains("&k");
        codes.reset = text.contains("&r");
        return codes;
    }

    private static String removeAllFormatCodes(String text) {
        return text.replaceAll("&[klmnor]", "");
    }

    private static int interpolateColor(int start, int end, double ratio) {
        return (int) Math.round((double) start + ratio * (double) (end - start));
    }

    private static String convertHexToLegacy(String hexCode) {
        return "§x§" + hexCode.charAt(0) + "§" + hexCode.charAt(1) + "§" + hexCode.charAt(2) + "§" + hexCode.charAt(3) + "§" + hexCode.charAt(4) + "§" + hexCode.charAt(5);
    }

    private static Component applyFormatCodes(Component component, FormatCodes codes) {
        if (codes.bold) {
            component = component.decoration(TextDecoration.BOLD, true);
        }
        if (codes.italic) {
            component = component.decoration(TextDecoration.ITALIC, true);
        }
        if (codes.underlined) {
            component = component.decoration(TextDecoration.UNDERLINED, true);
        }
        if (codes.strikethrough) {
            component = component.decoration(TextDecoration.STRIKETHROUGH, true);
        }
        if (codes.obfuscated) {
            component = component.decoration(TextDecoration.OBFUSCATED, true);
        }
        return component;
    }

    private static class FormatCodes {
        boolean bold = false;
        boolean italic = false;
        boolean underlined = false;
        boolean strikethrough = false;
        boolean obfuscated = false;
        boolean reset = false;

        private FormatCodes() {
        }

        String toLegacyString() {
            StringBuilder sb = new StringBuilder();
            if (this.bold) {
                sb.append("§l");
            }
            if (this.italic) {
                sb.append("§o");
            }
            if (this.underlined) {
                sb.append("§n");
            }
            if (this.strikethrough) {
                sb.append("§m");
            }
            if (this.obfuscated) {
                sb.append("§k");
            }
            if (this.reset) {
                sb.append("§r");
            }
            return sb.toString();
        }
    }
}
