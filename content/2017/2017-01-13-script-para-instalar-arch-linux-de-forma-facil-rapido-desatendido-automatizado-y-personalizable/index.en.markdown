---
pid: "204-en"
type: "post"
title: "Script to install Arch Linux easy, fast, unattended, automated and customizable way"
url: "/2017/01/script-to-install-arch-linux-easy-fast-unattended-automated-and-customizable-way/"
date: 2017-01-13T00:00:00+01:00
language: "en"
index: true
rss: true
sharing: true
comments: true
imagePost: "logotype:archlinux.svg"
tags: ["gnu-linux", "planeta-codigo", "software-libre"]
summary: "Arch Linux is one of the most customizable GNU/Linux distributions but whose installation requires reading a good amount of documentation, guides or manuals to know what commands to execute step by step in the _prompt_ of the system in which it leaves you the installation medium. Some users choose a distribution like Antergos, KaOS or Manjaro with a graphical installer and guided simply by the fact that they do not face Arch Linux installation from the command line. Still, executing a command and waiting for it to finish before entering another is slow and requires attention."
---

{{% post %}}

{{< logotype image1="archlinux.svg" image2="linux.svg" >}}

For a long time, installing a [GNU][gnu]/[Linux][linux] distribution has no more difficulty than installing an operating system like [Windows][windows], which consists of downloading the ISO image from the CD or DVD, burning it to a CD, DVD, or USB stick and start the system with the medium. The [distributions recommended for users who come from Windows or macOS][blogbitix-190] or do not have much computer knowledge have graphical or text-based installers and guided in several steps until completing the installation in less than half an hour. Distributions like [Ubuntu][ubuntu], [elementary OS][elementary] or [Linux Mint][linuxmint] make the installation without knowledge and be completed successfully by any user.

{{< image
    gallery="true"
    image1="image:debian-installer-text.png" optionsthumb1="300x200" title1="Debian installer text mode "
    image2="image:debian-installer-graphics.png" optionsthumb2="300x200" title2="Debian installer in graphical mode"
    caption="Guided graphical and text-based Debian installation" >}}

Other distributions based on different principles and intended users with other needs or preferences may require a little more knowledge in exchange for more advanced settings such as disk partitioning, disk encryption or software that is installed. Some distributions like [Arch Linux][archlinux] don't even provide any installer.

> It is targeted at the proficient GNU/Linux user, or anyone with a do-it-yourself attitude who is
> willing to read the documentation, and solve their own problems.

{{< tableofcontents >}}

### System installation with Arch Linux

In Arch Linux with his [way of doing things](https://wiki.archlinux.org/index.php/Arch_Linux) leaves the power to fully customize the system to his needs and preferences limited only by his determination of to get it. It only provides [the media to start the installation with][archlinux-download] starting with a terminal and a system _prompt_, an [installation guide][archlinux-install-guide] along with the [general recommendations][archlinux-general-recommendations] and [one of the best sources of information on GNU/Linux][archlinux-wiki] as well as complete information on every aspect that we need in the installation. After having read the relevant pages of the Arch Linux wiki, probably more than once and twice, until you understand it for the most part, you begin to make the recipe that contains the necessary commands until the installation is complete.

In many blogs and YouTube videos there are guides that contain the necessary commands and explanations. Any Arch Linux user with a blog has surely posted an article with their installation guide (yes, [I posted mine too][blogbitix-22]). A user who wants to install Arch Linux should read several of those articles in addition to the official installation guide. According to the Arch Linux philosophy, this learning process is considered necessary and allows for greater system understanding.

But knowing the commands to enter to install Arch Linux does not avoid having to type them one after the other and wait for the previous one to finish before entering the next one. Nor does everyone have the time to do it. Several [Arch Linux derived distributions](https://wiki.archlinux.org/index.php/Arch-based_distributions) such as [Antergos][antergos], [KaOS][kaos] or [Manjaro][manjaro] offer the more friendly graphical and guided installers that attract some users looking to use Arch Linux but not go through its unfriendly installation process. Fortunately, as it is a _rolling release_ distribution (constantly updated), you only have to do a single installation per computer throughout its life of use. But even for Arch Linux users doing a second install on a new machine is tiresome.

A few months ago I met [arch-anywhere][arch-anywhere] which is basically a bash _script_, with a guided and text-based installer that makes the installation of Arch Linux less laborious in a similar way to those existing in other more friendly distributions. However, there are two things that do not convince me about _arch-anywhere_, one is that it is not unattended, requiring you to answer several questions interactively, wait for it to finish, execute a command according to the previous answer and answer the next question. Another thing that doesn't convince me is that it uses its own ISO image and not the original Arch Linux image. For the rest, it offers a good level of customization, covering the most common needs of users such as customizing the partitioning, choosing the file system, if you want LVM, encryption, the desktop environment (GNOME, KDE, XFCE, ... ), kernel, boot loader (GRUB) and programs to install.

### The alis installer and supported features

Based on the idea of _arch-anywhere_ and spending a little time I have created a _script_ in [bash][bash] to install Arch Linux in an automated, unattended and customizable way to some extent although being useful for the most common configuration cases, without being essential to read a few dozen pages of the Arch Linux wiki or follow any step-by-step guide or tutorial. Some of the functionalities it supports are:

* **System**: UEFI, BIOS
* **Storage**: SATA, NVMe and MMC
* **Encryption**: root partition encrypted and no encrypted
* **Partition**: no LVM, LVM, LVM on LUKS, GPT on UEFI, MBR on BIOS
* **File system**: ext4, btrfs (with subvols), xfs, f2fs, reiserfs
* **Kernels**: linux, linux-lts, linux-hardened, linux-zen
* **Desktop environment**: GNOME, KDE, XFCE, Mate, Cinnamon, LXDE, i3-wm, i3-gaps
* **Display managers**: GDM, SDDM, Lightdm, lxdm
* **Graphics controller**: intel, nvidia and amd with optionally early KMS start. With intel optionally fastboot, hardware video acceleration and framebuffer compression.
* **Bootloader**: GRUB, rEFInd, systemd-boot
* **Custom shell**: bash, zsh, dash, fish
* **WPA WIFI network** installation
* **Periodic TRIM** for SSD storage
* Intel and AMD **processors microcode**
* Optional **swap file**
* **VirtualBox guest additions**
* **Kernel compression** and **custom parameters**
* **Users creation** and **add to sudoers**
* **systemd units enable or disable**
* **Multilib** support
* **Arch Linux** common and custom **packages installation**
* Flatpak utility installation and **Flatpak packages installation**
* SDKMAN utility installation and **SDKMAN packages installation**
* **AUR utility** installation (paru, yay, aurman) and **AUR packages installation**
* **Packages installation after base system installation** (preferred way of packages installation)
* Script for download installation and **recovery scripts** and configuration files
* **Retry packages download** on connection/mirror error
* **Packer support** for testing in VirtualBox
* **Installation log** with all commands executed and output in a file and/or **asciinema video**
* Wait after installation for an **abortable reboot**
* Fork the repository and **use your own configuration**

The recommended way to install Arch Linux is by learning which commands to execute and what each one does, first read the official Arch Linux installation guide and understand what the _script_ commands do. This _script_ is not official and therefore in the [Arch Linux forum in Spanish][archlinux-forums-es] or [in English][archlinux-forums] they will not be able to give you support although I will help you in the comments of this article if questions. Note that this _script_ removes all information from the installation device. If you try it, leave a comment at the end of the article.

Booted the system with the Arch Linux installation ISO image, you have to download the _script_, edit some environment variables for the alis configuration and start the installation process. Depending on the preferred desktop environment, if you want LVM, the type of the file system, if you want to encrypt the file system and packages to install, the values ​​of the variables will vary according to the preferences that initially have common values. Two variables that must be modified at least are _USER\_NAME_ and _USER\_PASSWORD_ with the name of the user and his password that the system will use.

The installation time required varies depending on the internet connection bandwidth and the type of storage, in my case with ADSL with a 1.2 MiB/s download and SSD, the installation of the base system without a desktop environment takes me approximately 10 minutes. With a fiber connection it will take significantly less time. The installation _script_ contains the same commands that would be necessary to perform the manual installation interactively.

Even if alis is not used, the bash code in the _script_ can be viewed as executable documentation or for reference if you choose to install manually. It contains the same commands that a user would manually run one after the other in an interactive installation. [alis][alis] is a _script_ that allows you to install Arch Linux in an unattended, automated and customizable way from Arch Linux.

These are the commands to use alis and its configuration file with which to customize the installation.

{{< code file="alis-install.sh" language="bash" options="" >}}
{{< code file="alis.conf" language="bash" options="" >}}

### Screenshots of different supported desktop environments

These are some screenshots with different desktop environments that I have tested with VirtualBox.

{{< image
    gallery="true"
    image1="image:archlinux-gnome.jpg" optionsthumb1="300x200" title1="GNOME"
    image2="image:archlinux-kde.jpg" optionsthumb2="300x200" title2="KDE"
    caption="GNOME and KDE desktop environments" >}}
{{< image
    gallery="true"
    image1="image:archlinux-xfce.jpg" optionsthumb1="300x200" title1="XFCE"
    image2="image:archlinux-cinnamon.jpg" optionsthumb2="300x200" title2="Cinnamon"
    caption="Xfce and Cinnamon desktop environments" >}}
{{< image
    gallery="true"
    image1="image:archlinux-lxde.jpg" optionsthumb1="300x200" title1="LXDE"
    image2="image:archlinux-mate.jpg" optionsthumb2="300x200" title2="Mate"
    caption="LXDE and Mate desktop environments" >}}
{{< image
    gallery="true"
    image1="image:archlinux-root-password.png" optionsthumb1="300x200" title1="Password request to decrypt root partition"
    caption="Password request to decrypt root partition" >}}

### Installation video of the base system with alis

The following video captured with [asciinema][asciinema] shows how using alis to install Arch Linux on a computer with the complete process of the basic system installation.

{{< asciinema id="418524" caption="Instalación de sistema base de Arch Linux con alis" >}}

### Alis source code

The installer code is not very complicated and much simpler than other similar interactive _scripts_, as it does not mix the installation commands with the interactive messages that ask questions and wait for answers, also after setting the variables and starting the installation process the it is done unattended until completed without requiring the user to enter more data interactively.

The commands that I have compiled from the recipe are the ones that we would execute one after another starting from the original Arch Linux installation media. The _main_ function contains the steps that the installation consists of, from partitioning and encrypting the disk to restarting after the installation is complete, the rest of the functions the commands of that installation step.

{{< code file="alis.sh" language="bash" options="" >}}

{{< sourcecode git="alis/tree/master/" >}}

{{% /post %}}
