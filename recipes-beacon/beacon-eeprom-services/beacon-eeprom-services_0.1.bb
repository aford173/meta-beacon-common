SUMMARY = "Beacon EmbeddedWorks EEPROM dump script"
DESCRIPTION = "Beacon EmbeddedWorks EEPROM dump script"
HOMEPAGE = ""

LICENSE = "MIT"

inherit systemd

DEPENDS = "beacon-eeprom-decoder"

S = "${WORKDIR}/${BP}"

IMX8M_PATH = "/sys/devices/platform/soc@0/30800000.bus/30a40000.i2c/i2c-2/2-0050/eeprom"

RZG_EEPROM_PATH = "/sys/devices/platform/soc/e66d8000.i2c/i2c-4/4-0050/eeprom"


do_compile() {

	if [ ${MACHINE} = "beacon-imx8mm-kit" ]; then
		echo "beacon-eeprom-decoder -i ${IMX8M_PATH}" > ${S}/dump-eeprom.sh
	fi

	if [ ${MACHINE} = "beacon-imx8mm-12" ]; then
		echo "beacon-eeprom-decoder -i ${IMX8M_PATH}" > ${S}/dump-eeprom.sh
	fi

	if [ ${MACHINE} = "beacon-imx8mm-4g-12" ]; then
		echo "beacon-eeprom-decoder -i ${IMX8M_PATH}" > ${S}/dump-eeprom.sh
	fi

	if [ ${MACHINE} = "beacon-imx8mm-4g-kit" ]; then
		echo "beacon-eeprom-decoder -i ${IMX8M_PATH}" > ${S}/dump-eeprom.sh
	fi

	if [ ${MACHINE} = "beacon-imx8mn-kit" ]; then
		echo "beacon-eeprom-decoder -i ${IMX8M_PATH}" > ${S}/dump-eeprom.sh
	fi

	if [ ${MACHINE} = "beacon-imx8mn-12" ]; then
		echo "beacon-eeprom-decoder -i ${IMX8M_PATH}" > ${S}/dump-eeprom.sh
	fi

	if [ ${MACHINE} = "beacon-imx8mn-2g-kit" ]; then
		echo "beacon-eeprom-decoder -i ${IMX8M_PATH}" > ${S}/dump-eeprom.sh
	fi

	if [ ${MACHINE} = "beacon-imx8mp-kit" ]; then
		echo "beacon-eeprom-decoder -i ${IMX8M_PATH}" > ${S}/dump-eeprom.sh
	fi
		if [ ${MACHINE} = "beacon-imx8mp-4g-kit" ]; then
		echo "beacon-eeprom-decoder -i ${IMX8M_PATH}" > ${S}/dump-eeprom.sh
	fi

	if [ ${MACHINE} = "beacon-rzg2h" ]; then
		echo "beacon-eeprom-decoder -i ${RZG_EEPROM_PATH}" > ${S}/dump-eeprom.sh
	fi

	if [ ${MACHINE} = "beacon-rzg2m" ]; then
		echo "beacon-eeprom-decoder -i ${RZG_EEPROM_PATH}" > ${S}/dump-eeprom.sh
	fi

	if [ ${MACHINE} = "beacon-rzg2n" ]; then
		echo "beacon-eeprom-decoder -i ${RZG_EEPROM_PATH}" > ${S}/dump-eeprom.sh
	fi
}

do_install () {
    install -d ${D}/${bindir}
    install -m 0755 ${S}/dump-eeprom.sh ${D}${bindir}
    
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system/
        install -m 0644 ${THISDIR}/dump_eeprom.service ${D}${systemd_unitdir}/system
    fi
}

SYSTEMD_SERVICE:${PN} = "dump_eeprom.service"

FILES_${PN} = " \
	 dump-eeprom.sh \
"
