# AstikorCarts Fix - 项目信息

## 📋 项目概述

**项目名称**: AstikorCarts Fix  
**维护者**: shiroha  
**版本**: 1.0.0
**Minecraft版本**: 1.20.x  
**基于**: 原版 AstikorCarts by MennoMax

## 🎯 项目目标

AstikorCarts Fix 是对原版 AstikorCarts 的修复和增强版本，主要目标是：

1. **修复原版bug** - 解决原版mod中存在的各种问题
2. **添加缺失功能** - 补充原版缺少的重要特性（如声音系统）
3. **优化用户体验** - 提升mod的整体使用体验
4. **保持兼容性** - 确保与原版完全兼容

## 🎵 主要改进

### v1.0.0 - 首个发布版本
- ✅ **新增马车滚动声音** - 原版缺失的重要功能
- ✅ **修复声音检测问题** - 解决移动检测不准确
- ✅ **消除重音问题** - 避免多个声音重叠
- ✅ **修复声音抽搐** - 地面拖动时声音稳定

## 🔧 技术特性

### 声音系统
- **动态音效调整** - 根据移动速度调整音量和音调
- **平滑过渡算法** - 使用插值确保声音变化自然
- **循环播放系统** - 专门的声音管理类
- **智能检测机制** - 准确识别马车移动状态

### 性能优化
- **累积移动检测** - 避免微小变化影响
- **减少更新频率** - 降低CPU占用
- **内存管理优化** - 及时清理无用声音实例

## 📦 文件结构

```
src/main/java/de/mennomax/astikorcarts/
├── client/sound/
│   └── CartRollingSound.java          # 新增：循环声音管理
├── entity/
│   └── AbstractDrawnEntity.java       # 修改：添加声音处理
└── AstikorCarts.java                  # 修改：注册声音事件

src/main/resources/assets/astikorcarts/
├── sounds/
│   └── entity/cart/rolling.ogg        # 新增：滚动声音文件
└── sounds.json                        # 修改：添加声音定义
```

## 🚀 发布计划

### 当前版本 (v1.0.0)
- [x] 马车滚动声音系统
- [x] 声音相关bug修复
- [x] 性能优化

### 未来计划
- [ ] 更多声音效果（连接、分离声音优化）
- [ ] 马车物理效果改进
- [ ] 新的马车类型
- [ ] 配置文件支持

## 📄 许可证

本项目基于原版 AstikorCarts 的 MIT 许可证，保持开源免费。

## 🙏 致谢

- **MennoMax** - 原版 AstikorCarts 作者
- **Minecraft Forge 团队** - 提供开发框架
- **社区用户** - 提供反馈和建议

---

**GitHub**: https://github.com/shiroha/astikor-carts-fix  
**作者**: shiroha  
**联系方式**: [GitHub Issues](https://github.com/shiroha/astikor-carts-fix/issues)
