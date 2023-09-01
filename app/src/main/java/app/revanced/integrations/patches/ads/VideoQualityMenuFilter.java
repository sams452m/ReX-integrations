package app.revanced.integrations.patches.ads;

import androidx.annotation.Nullable;

import app.revanced.integrations.settings.SettingsEnum;

public final class VideoQualityMenuFilter extends Filter {
    // Must be volatile or synchronized, as litho filtering runs off main thread and this field is then access from the main thread.
    public static volatile boolean isVideoQualityMenuVisible;

    public VideoQualityMenuFilter() {
        final var oldqualityflyout = new StringFilterGroup(
                SettingsEnum.ENABLE_OLD_QUALITY_LAYOUT,
                "quick_quality_sheet_content.eml-js"
        ));

        final var qualityFooter = new StringFilterGroup(
                SettingsEnum.HIDE_PLAYER_FLYOUT_PANEL_QUALITY_FOOTER,
                "quality_sheet_footer",
                "divider"
        );

        this.pathFilterGroups.addAll(
                oldqualityflyout,
                qualityFooter
        );
    }

    @Override
    boolean isFiltered(String path, @Nullable String identifier, String allValue, byte[] protobufBufferArray,
                       FilterGroupList matchedList, FilterGroup matchedGroup, int matchedIndex) {
        if (!path.contains("quality_sheet_content.eml-js")) {
            return false;
        }
        isVideoQualityMenuVisible = true;

        return super.isFiltered(path, identifier, allValue, protobufBufferArray, matchedList, matchedGroup, matchedIndex);
    }
}
