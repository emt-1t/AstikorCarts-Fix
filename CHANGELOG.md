# AstikorCarts Fix - 更新日志

**维护者**: shiroha
**基于**: 原版 AstikorCarts by MennoMax

## [1.0.0] - 2025-01-13 - 首个发布版本

### 🎵 新增功能
- **马车滚动声音系统**：为马车添加了完整的滚动声音功能
  - 动态音量调整：根据移动速度自动调整音量
  - 动态音调调整：根据移动速度自动调整音调
  - 平滑音效过渡：避免突然的音量变化
  - 智能声音管理：马车停止时声音自然淡出

### 🔧 修复内容
- **修复声音检测问题**：解决了 `getDeltaMovement()` 无法正确检测马车移动的问题
- **修复重音问题**：消除了多个声音实例同时播放导致的重音效果
- **修复声音抽搐**：解决了地面拖动时声音频繁变化的问题
- **优化移动检测**：使用实际移动距离而非速度向量进行检测
- **稳定地面拖动**：忽略Y轴变化，只关注水平移动距离

### 🎯 技术改进
- 添加了 `CartRollingSound` 循环声音类
- 实现了平滑的音量和音调插值算法
- 优化了声音播放的性能和稳定性
- 添加了累积移动距离检测机制

### 📁 新增文件
- `src/main/java/de/mennomax/astikorcarts/client/sound/CartRollingSound.java`
- `src/main/resources/assets/astikorcarts/sounds/entity/cart/rolling.ogg`

### 🎮 使用说明
1. 用动物（牛、马等）拖拽马车时会自动播放滚动声音
2. 声音会根据移动速度动态调整
3. 马车停止时声音会自然淡出
4. 支持飞行和地面拖动两种模式

---

## 基于版本
- **原版 AstikorCarts**: 由 MennoMax 开发的原始版本
- **AstikorCarts Redux**: 1.20.x 移植版本
- **AstikorCarts Fix**: 基于 Redux 版本的修复和增强版本
