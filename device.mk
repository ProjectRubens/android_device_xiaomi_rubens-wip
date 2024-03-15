#
# Copyright (C) 2022 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from mt6895-common
$(call inherit-product, device/xiaomi/mt6895-common/mt6895-common.mk)

# Inherit the proprietary files
$(call inherit-product, vendor/xiaomi/rubens/rubens-vendor.mk)

# Input
PRODUCT_COPY_FILES += \
    $(LOCAL_PATH)/configs/idc/uinput-fpc.idc:$(TARGET_COPY_OUT_SYSTEM)/usr/idc/uinput-fpc.idc \
    $(LOCAL_PATH)/configs/idc/uinput-goodix.idc:$(TARGET_COPY_OUT_SYSTEM)/usr/idc/uinput-goodix.idc \
    $(LOCAL_PATH)/configs/keylayout/uinput-fpc.kl:$(TARGET_COPY_OUT_SYSTEM)/usr/keylayout/uinput-fpc.kl \
    $(LOCAL_PATH)/configs/keylayout/uinput-goodix.kl:$(TARGET_COPY_OUT_SYSTEM)/usr/keylayout/uinput-goodix.kl


# Display
PRODUCT_PRODUCT_PROPERTIES += \
    debug.renderengine.backend=skiaglthreaded

# Overlays


PRODUCT_PACKAGES += \
    FrameworkResOverlay \
    SystemUIOverlay \
    SettingsOverlay

# Keymaster
PRODUCT_PACKAGES += \
    android.hardware.keymaster@4.0.vendor \
    android.hardware.keymaster@4.0-service \
    libkeymaster4.vendor:64 \
    libkeymaster4support.vendor:64 \
    libkeymaster_portable.vendor:64 \
    libkeymaster_messages.vendor:64 \
    libsoft_attestation_cert.vendor:64 \
    libpuresoftkeymasterdevice.vendor:64


# Recovery
PRODUCT_COPY_FILES += \
    $(LOCAL_PATH)/prebuilts/modules/modules.load.recovery:recovery/root/lib/modules/modules.load.recovery

# Rootdir
PRODUCT_PACKAGES += \
    init.project.rc \
    meta_init.rc

# Xiaomi Parts
PRODUCT_PACKAGES += \
    XiaomiParts

BUILD_BROKEN_SRC_DIR_IS_WRITABLE := true
