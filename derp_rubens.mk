#
# Copyright (C) 2023 The PixelExperience Project base on LineageOS
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from those products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)

# Inherit some common PixelExperience stuff.
$(call inherit-product, vendor/derp/config/common_full_phone.mk)

# Inherit from rubens device
$(call inherit-product, device/xiaomi/rubens/device.mk)

PRODUCT_DEVICE := rubens
PRODUCT_NAME := derp_rubens
PRODUCT_BRAND := Redmi
PRODUCT_MODEL := 22041211AC
PRODUCT_MANUFACTURER := Xiaomi

PRODUCT_CHARACTERISTICS := nosdcard

# Boot animation
TARGET_SCREEN_HEIGHT := 3200
TARGET_SCREEN_WIDTH := 1440
TARGET_BOOT_ANIMATION_RES := 1440

TARGET_SCREEN_DENSITY := 560

TARGET_SUPPORTS_QUICK_TAP := true

PRODUCT_GMS_CLIENTID_BASE := android-xiaomi

PRODUCT_BUILD_PROP_OVERRIDES += \
    PRIVATE_BUILD_DESC="missi-user 13 TP1A.220624.014 V14.0.4.0.TLNCNXM release-keys"

BUILD_FINGERPRINT := Redmi/rubens/rubens:13/TP1A.220624.014/V14.0.4.0.TLNCNXM:user/release-keys
