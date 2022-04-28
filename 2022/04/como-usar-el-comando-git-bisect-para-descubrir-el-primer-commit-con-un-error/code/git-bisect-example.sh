[picodotdev@archlinux alis (master $=)]$ git bisect start master 63575c4f1824c360dbf60aafaaec17401ea604bd
Biseccionando: faltan 5 revisiones por probar después de esta (aproximadamente 3 pasos)
[0aeacbab0fe55b71b272d670c76ab52fa2b206c4] Support LightDM autologin
[picodotdev@archlinux alis ((0aeacba...) $|BISECTING)]$ git bisect good
Biseccionando: faltan 2 revisiones por probar después de esta (aproximadamente 2 pasos)
[d5233aa726c2626d52de8981bee65dcfbf69ae61] Merge pull request #209 from shuriken1812/master
[picodotdev@archlinux alis ((d5233aa...) $|BISECTING)]$ git bisect bad
Biseccionando: faltan 0 revisiones por probar después de esta (aproximadamente 1 paso)
[f7a4790992f859aac6fde86b3b18a72e259545bd] Added pipewire-alsa and wireplumber
[picodotdev@archlinux alis ((f7a4790...) $|BISECTING)]$ git bisect bad
Biseccionando: faltan 0 revisiones por probar después de esta (aproximadamente 0 pasos)
[0b0a1ddca33814e254b3bc395af476156559aac4] Allow to choose display manager
[picodotdev@archlinux alis ((0b0a1dd...) $|BISECTING)]$ git bisect bad
0b0a1ddca33814e254b3bc395af476156559aac4 is the first bad commit
commit 0b0a1ddca33814e254b3bc395af476156559aac4
Author: pico.dev <pico.dev@gmail.com>
Date:   Sat Apr 23 14:48:22 2022 +0200

    Allow to choose display manager

 alis.conf |   4 +-
 alis.sh   | 148 +++++++++++++++++++++++++++++++++++++++++++++-----------------
 2 files changed, 112 insertions(+), 40 deletions(-)
 [picodotdev@archlinux alis ((0b0a1dd...) $|BISECTING)]$ git bisect reset
 [picodotdev@archlinux alis (master $=)]$ 