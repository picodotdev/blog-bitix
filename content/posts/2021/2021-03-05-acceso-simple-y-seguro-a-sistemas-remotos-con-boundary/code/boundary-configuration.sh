$ boundary scopes create -name bitix-scope -description "Example Bitix scope"

Scope information:
  Created Time:        Fri, 05 Mar 2021 15:24:33 CET
  Description:         Example Bitix scope
  ID:                  o_PuL7bdpoKw
  Name:                bitix-scope
  Updated Time:        Fri, 05 Mar 2021 15:24:33 CET
  Version:             1

  Scope (parent):
    ID:                global
    Name:              global
    Type:              global

  Authorized Actions:
    read
    update
    delete
$ boundary scopes create -name bitix-project -description "Example Bitix project" -scope-id=o_PuL7bdpoKw

Scope information:
  Created Time:        Fri, 05 Mar 2021 15:26:54 CET
  Description:         Example Bitix project
  ID:                  p_MPpTkaalUm
  Name:                bitix-project
  Updated Time:        Fri, 05 Mar 2021 15:26:54 CET
  Version:             1

  Scope (parent):
    ID:                o_PuL7bdpoKw
    Name:              bitix-scope
    Parent Scope ID:   global
    Type:              org

  Authorized Actions:
    read
    update
    delete
$ boundary host-catalogs create static -name bitix-host-catalog -description "Bitix host-catalog" -scope-id=p_MPpTkaalUm

Host Catalog information:
  Created Time:        Fri, 05 Mar 2021 15:30:22 CET
  Description:         Bitix host-catalog
  ID:                  hcst_8t06PaPJHK
  Name:                bitix-host-catalog
  Type:                static
  Updated Time:        Fri, 05 Mar 2021 15:30:22 CET
  Version:             1

  Scope:
    ID:                p_MPpTkaalUm
    Name:              bitix-project
    Parent Scope ID:   o_PuL7bdpoKw
    Type:              project

  Authorized Actions:
    read
    update
    delete

  Authorized Actions on Host Catalog\'s Collections:
    host-setss:
      create
      list
    hostss:
      create
      list
$ boundary host-sets create static -name=raspberrypi -description="Raspberry Pi host set" -host-catalog-id=hcst_8t06PaPJHK 

Host Set information:
  Created Time:        Fri, 05 Mar 2021 15:58:01 CET
  Description:         Raspberry Pi host set
  Host Catalog ID:     hcst_8t06PaPJHK
  ID:                  hsst_e6BJkM7PqR
  Name:                raspberrypi
  Type:                static
  Updated Time:        Fri, 05 Mar 2021 15:58:01 CET
  Version:             1

  Scope:
    ID:                p_MPpTkaalUm
    Name:              bitix-project
    Parent Scope ID:   o_PuL7bdpoKw
    Type:              project

  Authorized Actions:
    read
    update
    delete
    add-hosts
    set-hosts
    remove-hosts
$ boundary hosts create static -name raspberrypi -description "Static host for Raspberry Pi" -address "192.168.1.101" -host-catalog-id=hcst_8t06PaPJHK

Host information:
  Created Time:        Fri, 05 Mar 2021 15:38:18 CET
  Description:         Static host for Raspberry Pi
  Host Catalog ID:     hcst_8t06PaPJHK
  ID:                  hst_2eN5yaTE41
  Name:                raspberrypi
  Type:                static
  Updated Time:        Fri, 05 Mar 2021 15:38:18 CET
  Version:             1

  Scope:
    ID:                p_MPpTkaalUm
    Name:              bitix-project
    Parent Scope ID:   o_PuL7bdpoKw
    Type:              project

  Authorized Actions:
    read
    update
    delete

  Attributes:
    address:           192.168.1.101
$ boundary host-sets add-hosts --host=hst_2eN5yaTE41 -id=hsst_e6BJkM7PqR

Host Set information:
  Created Time:        Fri, 05 Mar 2021 15:58:01 CET
  Description:         Raspberry Pi host set
  Host Catalog ID:     hcst_8t06PaPJHK
  ID:                  hsst_e6BJkM7PqR
  Name:                raspberrypi
  Type:                static
  Updated Time:        Fri, 05 Mar 2021 16:01:36 CET
  Version:             2

  Scope:
    ID:                p_MPpTkaalUm
    Name:              bitix-project
    Parent Scope ID:   o_PuL7bdpoKw
    Type:              project

  Authorized Actions:
    read
    update
    delete
    add-hosts
    set-hosts
    remove-hosts

  Host IDs:
    hst_2eN5yaTE41
$ boundary targets create tcp -name raspberrypi -description "Raspberry Pi target" -default-port=22 -scope-id=p_MPpTkaalUm

Target information:
  Created Time:               Fri, 05 Mar 2021 15:42:04 CET
  Description:                Raspberry Pi target
  ID:                         ttcp_23T9SbQ7ce
  Name:                       raspberrypi
  Session Connection Limit:   1
  Session Max Seconds:        28800
  Type:                       tcp
  Updated Time:               Fri, 05 Mar 2021 15:42:04 CET
  Version:                    1

  Scope:
    ID:                       p_MPpTkaalUm
    Name:                     bitix-project
    Parent Scope ID:          o_PuL7bdpoKw
    Type:                     project

  Authorized Actions:
    read
    update
    delete
    add-host-sets
    set-host-sets
    remove-host-sets
    authorize-session
$ boundary targets add-host-sets -host-set=hsst_e6BJkM7PqR -id=ttcp_23T9SbQ7ce

Target information:
  Created Time:               Fri, 05 Mar 2021 15:42:04 CET
  Description:                Raspberry Pi target
  ID:                         ttcp_23T9SbQ7ce
  Name:                       raspberrypi
  Session Connection Limit:   1
  Session Max Seconds:        28800
  Type:                       tcp
  Updated Time:               Fri, 05 Mar 2021 15:59:03 CET
  Version:                    2

  Scope:
    ID:                       p_MPpTkaalUm
    Name:                     bitix-project
    Parent Scope ID:          o_PuL7bdpoKw
    Type:                     project

  Authorized Actions:
    read
    update
    delete
    add-host-sets
    set-host-sets
    remove-host-sets
    authorize-session

  Host Sets:
    Host Catalog ID:          hcst_8t06PaPJHK
    ID:                       hsst_e6BJkM7PqR