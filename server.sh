#!/usr/bin/env bash
HUGO_THEME=bitix hugo server --source="." --theme="bitix" --buildDrafts --buildFuture --watch --disableLiveReload
