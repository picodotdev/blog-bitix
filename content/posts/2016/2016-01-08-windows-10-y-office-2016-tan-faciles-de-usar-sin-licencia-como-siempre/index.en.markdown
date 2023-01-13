---
pid: "119-en"
type: "post"
title: "Windows 10 and Office 2016 as easy to use without license as always"
url: "/2016/01/windows-10-and-office-2016-as-easy-to-use-without-license-as-always/"
aliases: ["/2017/04/windows-10-and-office-2016-as-easy-to-use-without-license-as-always/"]
date: 2017-04-08T11:00:00+02:00
updated: 2020-09-26T12:00:00+02:00
index: true
rss: true
sharing: true
comments: true
language: "en"
imageHead: "image:windows-10-wallpaper.webp"
imagePost: "logotype:microsoft.svg"
tags: ["microsoft", "opinion"]
summary: "I commented in an article that it is surprising that Microsoft with the capacity that it has and being the devices connected to Internet mostly is not able to avoid using its software without license. I'm about to see if with Windows 10 and Office 2016 it's still as easy to use as always. I will also have a sample of the unfair fame of Windows but that allows Microsoft for the bloatware that the device manufacturers pre-installs with its operating system."
---

{{% post %}}

{{< logotype image1="microsoft.svg" image2="windows-10.svg" image3="microsoft-office.svg" >}}

This 2016 I have started nothing more and nothing less than installing the operating system and programs of a laptop. One of those favors that we as advanced computer users habitually do. I normally do it willingly, this time I received another in exchange for another kind, so perfect. The need for reinstallation came from the fact that the laptop was remarkably slow despite having a mechanical hard disk and it was not because the equipment was old or had little memory, the processor was an AMD Athlon x2 QL-65 with 4 GiB memory and 320GB hard drive, something that should be more than enough for the purpose of surfing the internet, watching movies, listening to music and do office tasks. It was slow to start and launching programs in addition to some antivirus message popups complaining that its trial period expired that appeared when entering the desktop. That was not all, a virus encrypted the personal files, photos and documents, asking in exchange for recovering them something that I did not even bother to see in detail, surely nothing good.

The computer had several bloatware programs typical of [Windows][windows] laptops provided by manufacturers, an antivirus that could fit the definition of what it tries to protect that expires after a while and leaves it unprotected, a music player that starts with the desktop and hides at the top of the screen in case you atypically need it. The manufacturers are the ones who pre-install these undesirable programs and will be the main culprits but if [Microsoft][microsoft] knows the bad name they give their systems, and so far has not prevented it, some guilt will have, if its operating system is an imposition in practically the whole laptops that are sold in stores, Do you know the [Windows tax][elblogdepicodev-57]?, they could impose some condition to allow Windows pre-install and make their users happier.

On the other hand since Windows Vista era the computer can be reinstalled and leave as factory included all the bloatware previously quoted but for this they reserve about 10 GiB of the hard disk space that is not usable by the user. With hard drives with capacities of 320/500 GB that are reserved 10 GiB is no bigger problem but more than one has asked because its available space does not correspond with the specifications of the equipment that bought.

So to fix the problems I did a clean reinstallation.

### Starting the installation

At the point of doing the reinstallation I had no problem, the computer had Windows 7 originally, had been upgraded to Windows 10 and activated correctly in the period of free upgrade offered by Microsoft. As I have done many times in years and with previous versions I start by [downloading the Windows 10 installation image media](https://www.microsoft.com/es-es/software-download/windows10ISO) and [the installation media for Microsoft Office, Home and Students](http://officecdn.microsoft.com/pr/492350f6-3a01-4f97-b9c0-c7c6ddf67d60/media/es-es/HomeStudent2019Retail.img). I continue [starting the Windows 10 installation step by step from scratch][blogbitix-231-en]. The installation media images are offered kindly by Microsoft so I had not to search them in the file sharing networks like in the older times.

In one of the installation steps I deleted all the partitions recovering the space reserved for the recovery partition. A few minutes of waiting, a couple of reboots and several privacy options deactivated that could give Windows 10 the name of spyware, supposedly for the sake of user, and after creating the user account the system starts with all the devices working properly including graphics card, network and WiFi. Windows is displayed as enabled.

I have to install the basic additional programs with the intention of being free software or respecting the licenses [Firefox][firefox] along with [AdBlockPlus][adblockplus], [VLC][vlc], [PeaZip][peazip], [ImgBurn][imgburn], [Acrobat Reader][adobe-acrobat-reader], [FreeFilesync][freefilesync], [uTorrent][utorrent] to which I add [Java][java]. Finally I am asked to install the office suite [Microsoft Office][microsoft-office] as it will be used, my intention fails, I propose to recommend some alternative option as [Google Docs][google-docs] or [LibreOffice][libreoffice]. However, Office is what they use, do they ask students to take a license that still costs about 125 € for students? 250 € for other mortals? It 's the start of 2016, it's the weekend and I do not want to think much about it, I download the latest version of Office and I install it.

### I try to activate Windows and Office

I start Word and it shows me a message that after about 30 days many of its functions will be disabled. The time comes to prove empirically once again what I said in [Microsoft does not care that you use Windows or Office without a license][blogbitix-285-en], I look for some activator in Google, there are hundreds of results, I download one from one of links that gives me some hope of not have a virus, immediately after download the executable file and Windows Defender warns that it have a virus, I run it anyway and click on the button to activate the Office, a message indicating that the activation has failed, I try several options without apparent effect to see if I am not right and Microsoft has done its job to avoid piracy. I search the links and I download another, virus warning, everything normal, I run it, icons begin to appear on the desktop of rare programs that are being installed and Firefox has been changed its home page, wow! this supposed activator was a virus, Defender was right, I will have to reinstall everything again.

### Searching an activator without virus

I put in the table the fat weapons, start a new reinstallation of Windows 10 and Office in a virtual machine with [VirtualBox][virtualbox] that will allow me to make the tests more easily using the snapshots of the system, if something goes wrong I will be able to return to an earlier point without having to reinstall all again. I aim to find some activator that works, long ago when using Windows in the first or second, maybe the third link no more than that in Google was the activator that worked, I think again that maybe Microsoft has protected better their software. I inform myself a little more on some of the hundreds of pages, I find that I have to disable Windows Defender, as explained in those pages activate Windows and Office (any of its versions) is as easy as I remembered, use an activator and push a button. I just did not find the right one? I put a little more interest and I look for the "official" page of an activator, it seems that I have found one with good feelings. I disable Windows Defender, I run the activator, the activator window opens and I select to activate Windows and Office pushing button, in a few seconds it ends and it informs that the activation was correct. I check that Windows is enabled and Office is also enabled.

### I start the third installation

I start the third installation again on the laptop and perform the activation in the same way as in the virtual machine, except Office only since the laptop has already a legal Windows license. Office reports that it is activated. I wish I had not been able to get it.

It is the beginning of 2016 and I have not made one but three installations of Windows and Office, more days than the year have. And I'm still right, using them without a license is still as easy as ever once found the right links in Google, Do a tuesday patch day will install some Windows update that discovers the crack applied to Office? I'll find out.

{{< image
    gallery="true"
    image1="image:windows-10.webp" optionsthumb1="300x200" title1="Windows 10"
    image2="image:microsoft-word-2016.webp" optionsthumb2="300x200" title2="Microsoft Word 2016"
    caption="Windows 10 and Microsoft Word 2016" >}}

### What are the alternatives to use an activator

Finally I used an activator but they are insecure, is better to purchase a license to one the alternative sellers to Microsoft that offer valid and legal license keys with prices much more cheaper than Microsoft. Microsoft sells the licenses of Windows starting at 145 € for Windows 10 and for Microsoft Office starting from 149 €, other sellers that can be found in [Amazon][amazon-affiliate] and [eBay][eBay] offer legal licenses much more cheaper, between 10 € and 15 €. So that some personalization options does not get disabled in Windows, does not get disabled the Microsoft Office documents edition and does not appears popup messages requesting the activation is necessary to [buy a license and activate Windows 10 and Office 2019][blogbitix-514-en].

* [Windows 10 license](https://www.microsoft.com/es-es/store/b/windows) in Microsoft.
* [Microsoft Office 2019 license](https://www.microsoft.com/es-es/microsoft-365/buy/compare-all-microsoft-365-products) in Microsoft.
* [Windows 10 Home license](https://amzn.to/333Df5X) in Amazon.
* [Windows 10 Pro license](https://amzn.to/3iZ4dBk) in Amazon.
* [Microsoft Office 2019 license](https://amzn.to/369oJfc) in Amazon.
* [Windows 10 Home license](https://www.ebay.es/sch/i.html?_from=R40&_trksid=p2334524.m570.l1313&_nkw=windows+10+home+key+64+bits&_sacat=0&LH_TitleDesc=0&_sop=2&_osacat=0&_odkw=windows+10+home+key+64) in eBay.
* [Windows 10 Pro license](https://www.ebay.es/sch/i.html?_from=R40&_trksid=p2334524.m570.l1313&_nkw=windows+10+professional+digital+key+64+bits&_sacat=0&LH_TitleDesc=0&_sop=2&_osacat=0&_odkw=windows+10+pro+digital+key+64+bits) in eBay.
* [Microsoft Office 2019 license](https://www.ebay.es/sch/i.html?_from=R40&_trksid=p2334524.m570.l1313&_nkw=microsoft+office+2019++key&_sacat=0&LH_TitleDesc=0&_sop=2&_osacat=0&_odkw=microsoft+office+2019+pro+key) in eBay.

If you can use software with a legal license but if not use some of the alternative options available for both Windows [choosing a GNU/Linux distribution][blogbitix-180] and [Office and any of its alternatives][blogbitix-143-en] that suits your needs.

{{% /post %}}
