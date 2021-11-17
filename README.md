# MiniChat
The simplest chat plugin you'll ever find!

## What is MiniChat?
MiniChat is a *super*-lightweight chat plugin, with just the bare minimum, for ease-of-use.

It has groups, parses PlaceholderAPI placeholders, and that's about it.

## Should I use MiniChat?
If you fall into the following categories, then yes!

```
1) Have an overcomplicated chat plugin that you barely use
2) Don't need built-in chat filters, or other features
3) Want the least setup possible
```

## How does it work?
in the `config.yml` file, there are "groups"

To use a group, assign yourself the permission `minichat.group.<group>`. If you have multiple, the first available one will be used. The last one will be used as the "default"

Groups look like this:
```yaml
groups:
  admin:
    prefix: "<red>[Admin] "
    name: "%player_name%"
    suffix: ": "
```
All 3 sections are optional, and default to either nothing, or the player name (depending on which one)