---
pid: "231-en"
type: "post"
title: "Download and install Windows 10 step by step from scratch"
url: "/2017/05/download-and-install-windows-10-step-by-step-from-scratch/"
date: 2017-05-09T22:30:00+02:00
updated: 2020-11-01T12:30:00+01:00
language: "en"
index: true
rss: true
sharing: true
comments: true
promoted: false
imageHead: "image:windows-10-wallpaper.jpg"
imagePost: "logotype:microsoft.svg"
tags: ["microsoft", "planeta-codigo", "software", "windows"]
summary: "A virus, _bloatware_ pre-installed on many laptops, or after installing and uninstalling programs in Windows can cause the computer to slow down, display error messages, or a virus to encrypt personal files. In these cases, a simple, fast and reliable common solution to use the computer normally again is to reinstall Windows 10 from scratch. It is not complicated but for a user with little computer knowledge it is not an easy task. In this article I explain how to download and install the Windows 10 operating system step by step from scratch for free."
---

{{% post %}}

{{< logotype image1="microsoft.svg" image2="windows-10.svg" >}}

Almost all laptops sold in the most popular department stores are pre-installed with the Windows operating system. But manufacturers with the intention of providing more capabilities and functionalities than those included in [Windows][windows] install numerous programs that make up the well-known _bloatware_ that on many occasions make a completely new computer slower than normal when starting the system, when starting programs or working with them. Among the programs that manufacturers usually pre-install are antivirus that after a while are deactivated since they are usually evaluation versions and can leave the computer unprotected against viruses and malicious software, Windows 10 already incorporates one, Windows Defender, and for most of users is sufficient for its acceptable effectiveness. Other programs that manufacturers usually install are video players or multimedia software, in most cases unnecessary since Windows itself already incorporates the capabilities to work with multimedia files such as photos or videos. It may also be the case that after an update or the installation of a program the computer begins to show failures.

In the above cases, a reinstallation of Windows from scratch is necessary. Many users do not have advanced computer or technology knowledge and do not know the steps to perform a reinstall. In this article I will explain in detail, step by step and from scratch, what are the steps to take to install Windows 10.

{{< tableofcontents >}}

### Minimum requirements

The first thing is to know that Windows 10 to function correctly has minimum requirements for the computer on which it will be installed, it was marketed in July 2015 and any computer after that date will already meet the minimum requirements and also the computers that meet with Windows 7 or pre-installed with this previous version. In the page of [Specifications and system requirements for Windows](https://www.microsoft.com/es-es/windows/windows-10-specifications) are detailed a good amount of informative notes and the requirements that are the following:

* Processor: 1 GHz or faster processor or SoC
* RAM: 1 gigabyte (GiB) for 32-bit or 2 GB for 64-bit
* Hard disk space: 16 GB for a 32-bit OS or 20 GB for a 64-bit OS
* Graphics card: DirectX 9 or later with a WDDM 1.0 driver
* Screen: 800 x 600

These are the basic requirements, the recommended ones for a good user experience are to have 4 GiB of memory and with this memory the next aspect with which an improvement is most noticeable is to have a solid state hard disk or SSD instead of mechanical since they are several magnitudes faster both in reading and writing.

Any new computer meets these requirements but perhaps some do not have an SSD hard disk, these disks are more expensive and offer less capacity although they have already become cheaper and are quite affordable and their capacity from 128 GiB or 256 GiB are enough for many users. Paying a little more for a computer with an SSD disk is a good decision, a computer with a mechanical disk may take 1 or 2 minutes to start up, one with an SSD less than 10 seconds, which is just an example between the difference between one and the other.

### Windows 10 versions

Windows 10 has different versions, the differences between them are in the features they support.

* _Home_: it is the version used mostly and sufficient for most users to be installed on home computers.
* _Pro_: this version includes all the functionalities of the _Home_ edition with some additional ones normally necessary in business environments. Some of its additional functionalities are Active Directory, Remote Desktop, BitLocker, virtualization with Hyper-V and _Windows Defender Device Guard_.
* _Pro for Workstations_: is the version that allows you to use Windows on high-performance computers with Intel Xeon or AMD Epyc processors, large amounts of memory and some more advanced options.

### Backup

Once you know that the equipment meets the minimum requirements, you have to make a backup copy of the documents, photos and other personal files that the equipment has and you want to keep on an external hard drive or USB memory, if there is a lot of data instead of copying and pasting with the file explorer you can make the [backup with the FreeFileSync program][blogbitix-144] and restore the data also with the same program once Windows is reinstalled.

{{< image
    gallery="true"
    image1="image:freefilesync.png" optionsthumb1="200x150" title1="Backup with FreeFileSync"
    caption="Backup with FreeFileSync" >}}

### How to download Windows 10 for free

In previous versions of Windows it had to be searched on file-sharing or P2P networks as a torrent. It is Microsoft itself that offers by direct download the Windows 10 ISO image for free from its own page and a tool for creating the USB or DVD media from which to start the installation. Simply [download the Windows 10 installation media](https://www.microsoft.com/en-us/software-download/windows10) to your computer by accessing the download page with a web browser, the installation media includes all versions of Windows, _Home_, _Pro_ and _Pro for Workstations_. With a good internet connection the download is fast, it does not take more than a few minutes.

### Creating the installation media

Depending on the Windows update downloaded, the screenshots or steps will vary but will be largely similar. Accessing the Windows 10 download page from a Windows 7, Windows 8.1 or Windows 10 system will display the option to download the Media Creation Tool or _Media Creation Tool_. Once downloaded and executed, the tool also allows the download of Windows 10 and the creation of media either a DVD or USB memory. In the case of the USB memory, it must have a capacity of at least 8 GiB and all the data it has will be lost, so your data must be saved in another external drive.

To install Windows 10, it is more advisable to use a USB memory with the _Media Creation Tool_ program since it is faster than using a disused DVD disc for which some systems no longer even include a DVD reader.

{{< image
    gallery="true"
    image1="image:windows-media-creation-tool-01.png" optionsthumb1="200x150" title1="Media Creation Tool"
    image2="image:windows-media-creation-tool-02.png" optionsthumb2="200x150" title2="Media Creation Tool" >}}
{{< image
    gallery="true"
    image1="image:windows-media-creation-tool-03.png" optionsthumb1="200x150" title1="Media Creation Tool"
    image2="image:windows-media-creation-tool-04.png" optionsthumb2="200x150" title2="Media Creation Tool"
    caption="Creating the installation media" >}}

### Start Window 10 installation

Once the Windows 10 installation media is ready in the form of a USB memory or DVD with the computer turned off and the installation media connected to a USB port or inserted in the DVD reader, it must be started so that it starts from the installation media. Depending on the manufacturer, starting the computer from the installation media varies slightly but the press of a key is usually used when the computer starts, the key is usually F2, F8, F10, F12 or the Escape key that allows you to select the drive from which the computer starts where you have to choose the USB memory or DVD.

It may be that the key is used to access the UEFI BIOS where you can also select the boot order of the units that also varies according to the manufacturer, the UEFI BIOS contains important configuration parameters of the computer so you must be careful in the things that are modified in them so if you are not sure of the changes you are making do not save them, luckily the latest versions of the UEFI BIOS are more intuitive and with easier to use graphical interfaces.

Depending on the manufacturer and even models from the same manufacturer, the access key to start from the installation media varies:

* Acer: F2 or Delete
* Asus: F2 or F10
* Dell: F2, F1, Delete, F12 or F3
* HP: F10 or Esc
* Lenovo: F1 or F2
* Sony: F2, F3, F1 or assist key
* Toshiba: F2, F1, Esc

In the following pages you can find several possible keys to enter the BIOS and change the system boot drive according to the brand, [I](http://www.makeuseof.com/tag/enter-bios-computer/), [II](https://www.lifewire.com/bios-setup-utility-access-keys-for-major-bios-manufacturers-2624461) and [III](https://www.lifewire.com/bios -setup-utility-access-keys-for-popular-computer-systems-2624463).

It may be the case that when the equipment starts, it shows a message with the key to press if it is necessary to try until it is found. After pressing the power button shortly after or as a logo is displayed is when the key must be pressed.

### Windows 10 setup wizard

One of the successes of Windows 10 is that the installation offers a wizard with which after completing several steps and pressing buttons _Next_ the system is installed and ready to use. Installation is complete in less than an hour.

Among the steps are the selection of the language, the version of the _Home_ or _Pro_ system (the first is enough), the reading of the terms of the license of use that few people read completely due to its length and its little understandable legal jargon. If the computer has a license, the possibility of entering the product key to activate Windows will be offered, if you do not have it, it is possible to skip this step by pressing the option _I do not have a product key_ and use Windows without limitations for a while, after some time in any case the limitations are very slight and Windows 10 is usable normally.

The _Customized installation is recommended: install only Windows_ and do not keep previous files and settings to avoid problems or inherit them from the previous system. It is also recommended to delete all partitions on the disk and let Windows create the necessary ones. After finishing copying files to the computer showing the installation progress, a reboot will be performed automatically.

{{< image
    gallery="true"
    image1="image:instalacion-windows-10-01.png" optionsthumb1="200x150" title1="Windows 10 installation"
    image2="image:instalacion-windows-10-02.png" optionsthumb2="200x150" title2="Windows 10 installation"
    image3="image:instalacion-windows-10-03.png" optionsthumb3="200x150" title3="Windows 10 installation" >}}
{{< image
    gallery="true"
    image1="image:instalacion-windows-10-04.png" optionsthumb1="200x150" title1="Windows 10 installation"
    image2="image:instalacion-windows-10-05.png" optionsthumb2="200x150" title2="Windows 10 installation"
    image3="image:instalacion-windows-10-06.png" optionsthumb3="200x150" title3="Windows 10 installation" >}}
{{< image
    gallery="true"
    image1="image:instalacion-windows-10-07.png" optionsthumb1="200x150" title1="Windows 10 installation"
    image2="image:instalacion-windows-10-08.png" optionsthumb2="200x150" title2="Windows 10 installation"
    image3="image:instalacion-windows-10-09.png" optionsthumb3="200x150" title3="Windows 10 installation"
    caption="Windows 10 installation" >}}

#### Basic configuration

After installing Windows, it offers a new wizard where some additional things about the system are customized. Some options to select in these steps before starting to use Windows 10 are:

* The region or country of the user.
* The layout or arrangement of the keyboard keys.
* Login user either with a Microsoft account or with an offline account and password. If the computer is connected to the internet, the configuration wizard asks to use a Microsoft account, if you want to use a local account you have to disconnect the network cable.
* If you want to use Cortana as a personal assistant.
* Some privacy options that are recommended to disable so that Windows does not collect information from our device even if it is anonymous data.

{{< image
    gallery="true"
    image1="image:configuracion-windows-10-01.png" optionsthumb1="200x150" title1="Windows 10 Configuration"
    image2="image:configuracion-windows-10-02.png" optionsthumb2="200x150" title2="Windows 10 Configuration"
    image3="image:configuracion-windows-10-03.png" optionsthumb3="200x150" title3="Windows 10 Configuration" >}}
{{< image
    gallery="true"
    image1="image:configuracion-windows-10-04.png" optionsthumb1="200x150" title1="Windows 10 Configuration"
    image2="image:configuracion-windows-10-05.png" optionsthumb2="200x150" title2="Windows 10 Configuration"
    image3="image:configuracion-windows-10-06.png" optionsthumb3="200x150" title3="Windows 10 Configuration" >}}
{{< image
    gallery="true"
    image1="image:configuracion-windows-10-07.png" optionsthumb1="200x150" title1="Windows 10 Configuration"
    image2="image:configuracion-windows-10-08.png" optionsthumb2="200x150" title2="Windows 10 Configuration"
    image3="image:configuracion-windows-10-09.png" optionsthumb3="200x150" title3="Windows 10 Configuration" >}}
{{< image
    gallery="true"
    image1="image:configuracion-windows-10-10.png" optionsthumb1="200x150" title1="Windows 10 Configuration"
    image2="image:configuracion-windows-10-11.png" optionsthumb2="200x150" title2="Windows 10 Configuration"
    image3="image:configuracion-windows-10-12.png" optionsthumb3="200x150" title3="Windows 10 Configuration" >}}
{{< image
    gallery="true"
    image1="image:configuracion-windows-10-13.png" optionsthumb1="200x150" title1="Windows 10 Configuration"
    image2="image:configuracion-windows-10-14.png" optionsthumb2="200x150" title2="Windows 10 Configuration"
    image3="image:configuracion-windows-10-15.png" optionsthumb3="200x150" title3="Windows 10 Configuration" >}}
{{< image
    gallery="true"
    image1="image:configuracion-windows-10-16.png" optionsthumb1="200x150" title1="Windows 10 Configuration"
    image2="image:configuracion-windows-10-17.png" optionsthumb2="200x150" title2="Windows 10 Configuration"
    image3="image:configuracion-windows-10-18.png" optionsthumb3="200x150" title3="Windows 10 Configuration" >}}
{{< image
    gallery="true"
    image1="image:configuracion-windows-10-19.png" optionsthumb1="200x150" title1="Windows 10 Configuration"
    image2="image:configuracion-windows-10-20.png" optionsthumb2="200x150" title2="Windows 10 Configuration"
    caption="Configuración inicial de Windows 10" >}}

After the Windows 10 configuration steps, prepare the system for the first login.

{{< image
    gallery="true"
    image1="image:primer-inicio-sesion-windows-10-01.png" optionsthumb1="200x150" title1="First start with Windows 10"
    image2="image:primer-inicio-sesion-windows-10-02.png" optionsthumb2="200x150" title2="First start with Windows 10"
    image3="image:primer-inicio-sesion-windows-10-03.png" optionsthumb3="200x150" title3="First start with Windows 10"
    caption="First start with Windows 10" >}}

### Using Windows and what to do after installation

If the computer already had a Windows license and was activated, surely Windows 10 will already recognize it as activated. It is also very likely that Windows 10 will be able to automatically recognize all the hardware that you have in the computer, including the graphics card, to use the maximum resolution offered by the screen, so if necessary, you must install an additional device driver by downloading it from the official website of the product manufacturer.

At this point Windows 10 can start to use and perhaps install the basic software and programs that you are going to use as an office suite either [Microsoft Office][microsoft-office] or one of the best [4 alternatives to Microsoft Office][blogbitix-143-en], a web browser like [Firefox][firefox] or [Chrome][google-chrome] with the plugin to block ads [AdBlockPlus][adblockplus] instead of using [Microsoft Edge][microsoft-edge ] which is built-in by default. It is not necessary to install any antivirus since Windows 10 already integrates its own, which is also one of the best.

Even without a license and without activating Windows 10 it is perfectly usable, perhaps after a while you will not be able to change some customization options such as the desktop background or window themes but nothing important that prevents you from using it. And in any case [Windows 10 and Office 2016 are still as easy to use without a license as ever][blogbitix-119].

{{< image
    gallery="true"
    image1="image:windows-10-01.png" optionsthumb1="200x150" title1="Windows 10"
    image2="image:windows-10-02.png" optionsthumb2="200x150" title2="Windows 10"
    image3="image:windows-10-03.png" optionsthumb3="200x150" title3="Windows 10" >}}
{{< image
    gallery="true"
    image1="image:windows-10-04.png" optionsthumb1="200x150" title1="Windows 10"
    image2="image:windows-10-05.jpg" optionsthumb2="200x150" title2="Windows 10"
    caption="Windows 10" >}}

#### Buy a license and activate Windows 10

Installing and using Windows 10 is free but after a few days after installation it must be activated with a valid license code, if it is not activated, some customization options such as changing the desktop background are disabled, although they do not prevent its use.

Windows 10 licenses sold by Microsoft are not cheap, between € 145 and € 439 depending on the version. Some sellers offer cheap and legal licenses at very reduced prices of between $ 10 and $ 15 as I explain in the article [How to buy a license and activate Windows 10 and Microsoft Office 2019][blogbitix-514-en].

### Alternative to Windows with GNU/Linux distributions

You should know that Windows is not the only operating system option that you can install, if you want you can try any of the [GNU][gnu]/[Linux][linux] distributions as an alternative to Windows and discover the world of free software that it respects your rights and it does not take them away as proprietary software, you can read the article [choose a GNU/Linux distribution according to the user or team][blogbitix-190] and [install Ubuntu step by step from scratch][blogbitix-232].

The following are some screenshots of several of the most famous GNU/Linux distributions.

{{< image
    gallery="true"
    image1="image:ubuntu.png" optionsthumb1="200x150" title1="Ubuntu with Unity desktop environment"
    image2="image:elementary-os.png" optionsthumb2="200x150" title2="elementaryOS"
    caption="Ubuntu and elementaryOS" >}}
{{< image
    gallery="true"
    image1="image:fedora.png" optionsthumb1="200x150" title1="Fedora with GNOME desktop environment"
    image2="image:opensuse.png" optionsthumb2="200x150" title2="openSUSE with KDE desktop environment"
    caption="Fedora and openSUSE" >}}

{{% /post %}}
