#
# Copyright (C) 2022 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from mt6895-common
-include device/xiaomi/mt6895-common/BoardConfigCommon.mk

DEVICE_PATH := device/xiaomi/rubens

BOARD_HAS_MTK_HARDWARE := true

# Assert
TARGET_OTA_ASSERT_DEVICE := rubens

# Bootloader
TARGET_BOOTLOADER_BOARD_NAME := rubens

# Init
TARGET_INIT_VENDOR_LIB := //$(DEVICE_PATH):libinit_rubens
TARGET_RECOVERY_DEVICE_MODULES := libinit_rubens

# Kernel
TARGET_PREBUILT_KERNEL := $(DEVICE_PATH)/prebuilts/kernel
TARGET_PREBUILT_DTB := $(DEVICE_PATH)/prebuilts/dtb
BOARD_PREBUILT_DTBOIMAGE := $(DEVICE_PATH)/prebuilts/dtbo.img

BOARD_BOOTCONFIG += androidboot.init_fatal_reboot_target=bootloader

TARGET_KERNEL_CONFIG := mikrn_rubens_stability_defconfig

BOARD_MKBOOTIMG_ARGS += --dtb $(TARGET_PREBUILT_DTB)

# Kernel modules
BOARD_VENDOR_RAMDISK_KERNEL_MODULES := $(wildcard $(DEVICE_PATH)/prebuilts/modules/*.ko)
BOARD_VENDOR_RAMDISK_KERNEL_MODULES_LOAD := $(strip $(shell cat $(DEVICE_PATH)/prebuilts/modules/modules.load))

#Vendor_dlkm
BOARD_USES_VENDOR_DLKMIMAGE := true
BOARD_VENDOR_KERNEL_MODULES := $(wildcard vendor/xiaomi/rubens/propietary/vendor_dlkm/lib/modules/*.ko)
BOARD_VENDOR_KERNEL_MODULES_LOAD := $(strip $(shell cat vendor/xiaomi/rubens/proprietary/vendor_dlkm/lib/modules/modules.load))

# Properties
TARGET_VENDOR_PROP += $(DEVICE_PATH)/vendor.prop

# Temproraily disable SELinux for testing
BOARD_KERNEL_CMDLINE += androidboot.selinux=permissive
